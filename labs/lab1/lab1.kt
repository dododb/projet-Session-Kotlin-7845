
open class Wagon
{
    protected open var wagonName = "wagon"

    var suivant: Wagon? = null
    fun afficher_wagon() { println(selfPrint()) }
    fun afficher_train() {
        afficher_wagon()
        suivant?.afficher_train()
    }
    protected open fun selfPrint(): String { return wagonName + ":" }
}

class Voyageur(var nom: String)
{
    private var wagon: WagonVoyageur? = null
    fun monter(wv: WagonVoyageur?) {
        descendre()
        wagon = wv
        wagon?.voyageurs?.add(this)
    }

    fun descendre()
    {
        if(wagon != null) {
            wagon?.voyageurs?.remove(this)
            wagon = null
        }
    }
}

open class WagonVoyageur : Wagon()
{
    override var wagonName: String = "VOYAGEUR"
    val voyageurs: ArrayList<Voyageur> = ArrayList<Voyageur>()

    override fun selfPrint(): String {
        val voyageurs_nom = voyageurs.map{ it.nom }
        return super.selfPrint() + " voyageur(s)=" + voyageurs_nom.joinToString()
    }
}

class Marchand(var nom: String) {}

interface IWagonMarchandise
{
    var marchand: Marchand?
    fun affreter(m: Marchand) { marchand = m}
}

class WagonMarchandise : Wagon(), IWagonMarchandise
{
    override var wagonName: String =  "MARCHANDISE"
    override var marchand: Marchand? = null

    override fun selfPrint(): String {
        return (super.selfPrint() + " marchand=" + marchand?.nom)
    }
}

class WagonDouble : WagonVoyageur(), IWagonMarchandise
{
    override var wagonName: String =  "DOUBLE"
    override var marchand: Marchand? = null

    override fun selfPrint(): String {
        return (super.selfPrint() + " marchand=" + marchand?.nom)
    }
}


fun main(args: Array<String>) {
    var v1 = Voyageur("Philémon")
    val v2 = Voyageur("Peninna")
    val m = Marchand("Far★Star")

    val w1 = WagonVoyageur()
    v1.monter(w1)
    v2.monter(w1)

    var w2 = WagonMarchandise()
    w1.suivant = w2
    w2.affreter(m)

    var w3 = WagonMarchandise()
    w2.suivant = w3
    w3.affreter(m)

    var w4 = WagonVoyageur()
    w3.suivant = w4
    v2.monter(w4)

    var w5 = WagonDouble()
    w4.suivant = w5
    v2.monter(w5)
    v1.monter(w5)
    w5.affreter(m)

    w1.afficher_train()
}