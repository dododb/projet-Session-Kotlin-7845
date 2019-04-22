class Annee internal constructor(s: String) {
    /** Numéro de l’année: ne peut être négatif ou nul */
    internal var numero: Int = 0

    /** AC=après la conquête de Aegon et l’arrivée à PortRoyal  */
    internal var ac: Boolean = false

    init {
        // TODO
        val params = s.split(" ")
        //System.out.println("Annee l11 : " + params[0]);
        numero = Integer.parseInt(params[0])
        ac = params[1] == "AC"
    }

    override fun toString(): String {
        // TODO
        return numero.toString() + " " + if (ac) "AC" else "BC"
    }
}