fun main(args: Array<String>)
{
    val nonmutableVariable = Personne()
    //nonmutableVariable = Personne() // impossible de réasigner la variable
    nonmutableVariable.eye = Eye(15) // possibilité de réasigner les objets mutable a l'interieur d'un objet non mutable
    //nonmutableVariable.noze = Noze(3) // impossibilité de réasigner les objets non mutable a l'interieur d'un objet non mutable

    var mutableVariable = Personne()
    mutableVariable = Personne() // possibilité de réasigner la variable
    mutableVariable.eye = Eye(15) // possibilité de réasigner les objets mutable a l'interieur d'un objet non mutable
    //mutableVariable.noze = Noze(3) // impossibilité de réasigner les objets non mutable a l'interieur d'un objet non mutable
}

class Personne
{
    var eye: Eye? = Eye(10)
    val noze = Noze()
}

class Eye(var size: Int)
{
}

class Noze(val narineCount: Int = 2)