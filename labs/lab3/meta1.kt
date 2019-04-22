package lab3

import kotlin.reflect.*
import kotlin.reflect.full.primaryConstructor

fun <T: Any>strinit(klass: KClass<T>, data: String) : T?
{
    val args = data.split(",")
    val argsArray = args.toTypedArray()

    val c = klass.primaryConstructor
    val instance = c?.call(*argsArray)
    return instance
}

public class Personne(val nom: String, val prenom: String)
{
    override fun toString(): String {
        return "$nom $prenom"
    }
}

public class Meta1
{
	public fun main(args: Array<String>) {
	    val p = strinit(Personne::class, "jon,Snow")
	    println(p)
	}
}
