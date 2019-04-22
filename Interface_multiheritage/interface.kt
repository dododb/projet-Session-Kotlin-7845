interface IA
{
    val i: Int //obligation de le recreer dans la classe qui l'implemente
    fun foo() { println(i)}
    fun bar() { println("toto$i")}
}

class A(override val i: Int) : IA
{

}

fun main()
{
    val a = A(10)
    a.foo()
}