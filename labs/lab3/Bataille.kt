class Bataille {
    var nom: String = ""
    var annee: Annee? = null
    var decisive: Boolean = false

    fun GetNom(): String { return nom }
    fun SetNom(nom: String) { this.nom = nom }

    fun GetAnnee(): Annee? { return annee }
    fun SetAnnee(annee: Annee) { this.annee = annee }

    fun GetDecisive(): Boolean { return decisive }
    fun SetDecisive(decisive: Boolean) { this.decisive = decisive }

    override fun toString(): String {
        // TODO
        return nom + " " + annee?.toString() + " " + decisive
    }
}
