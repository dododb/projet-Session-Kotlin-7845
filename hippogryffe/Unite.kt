interface IUnite
{
    val nom: String
    var pv: Int

    fun action(texte: String) = println("$this $texte")

    fun attaque(ennemi: Unite)
    {
        ennemi.pv--
        action("attaque $ennemi")
    }
}

interface ICheval : IUnite
{
    fun galope() = action("gallope à la vitesse du vent.")

    override fun attaque(ennemi: Unite)
    {
        ennemi.pv -= 2
        action("inflige une ruade à $ennemi")
    }
}

interface IAigle : IUnite
{
    fun vole() = action("fend les nuages.")

    fun attaque(ennemi: Aigle)
    {
        ennemi.pv -= 2
        action("vole dans les plumes de $ennemi")
    }
}


abstract class Unite(override val nom: String, override var pv: Int) : IUnite
{
    override fun toString(): String = "$nom($pv)"
}

class Cheval(nom: String) : Unite(nom, 4), ICheval

class Aigle(nom: String) : Unite(nom, 3), IAigle

class Hippogriffe(nom: String) : Unite(nom, 5), ICheval, IAigle

fun main()
{
    val buck = Hippogriffe("buck")
    buck.vole()
    buck.galope()

    val grand = Aigle("Grand Aigle")
    grand.attaque(buck)
    buck.attaque(grand)

    val fluttershy = Cheval("fluttershy")
    fluttershy.attaque(buck)
    buck.attaque(fluttershy)
}