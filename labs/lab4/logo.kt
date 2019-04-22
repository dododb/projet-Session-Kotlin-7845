package logo

abstract class Logo

open class LogoSimple : Logo()

class LogoForward(val distance: Int) : LogoSimple()
{
    override fun toString(): String {
        return "FD $distance"
    }
}

class LogoRight(val angle: Int) : LogoSimple()
{
    override fun toString(): String {
        return "RT $angle"
    }
}

open class LogoComplex : Logo()

class LogoProg : LogoComplex()
{
    val logos = ArrayList<Logo>()

    override fun toString(): String {
        return logos.joinToString(" ") { "$it" }
    }
}

class LogoRepeat(val number: Int) : LogoComplex()
{
    val body = LogoProg()

    override fun toString(): String {
        var value = ""
        for(x in 1..number) value += body
        return value
    }
}

class LogoWriter
{
    val stack = ArrayList<LogoProg>()

    init {
        stack.add(LogoProg())
    }

    fun main(): LogoProg {
        return stack.first()
    }

    fun add(logo: Logo) : LogoWriter
    {
        stack.last().logos.add(logo)
        return this
    }

    fun fd(distance: Int): LogoWriter
    {
        return add(LogoForward(distance))
    }

    fun rt(angle: Int): LogoWriter
    {
        return add(LogoRight(angle))
    }

    fun repeat(number: Int): LogoWriter
    {
        val rpt = LogoRepeat(number)
        add(rpt)
        stack.add(rpt.body)
        return this
    }

    fun fini(): LogoWriter
    {
        stack.removeAt(0)
        return this
    }
}

fun logo(): LogoWriter { return LogoWriter()}

fun logo_star(): LogoProg { return logo().repeat(5).fd(50).rt(144).fini().main() }

fun logo_home(): LogoProg { return logo().repeat(4).fd(50).rt(90).fini().fd(50).rt(-135).fd(35).rt(90).fd(35).rt(225).main() }
fun main()
{
    println(logo_star())
    println(logo_home())
}