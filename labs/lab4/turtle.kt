import logo.*
import java.awt.DisplayMode

class Turtle
{
    var x = 250.0

    var y = 100.0

    var angle = 0.0

    override fun toString(): String {
        return "($x,$y;$angle)"
    }

    fun fd(distance: Int)
    {
        x += distance * Math.cos(angle)
        y += distance * Math.sin(angle)
    }

    fun rt(angle: Int)
    {
        this.angle += angle
        this.angle %= 360
    }
}




fun LogoForward.apply(turtle: Turtle) { turtle.fd(distance) }

fun LogoRight.apply(turtle: Turtle) { turtle.rt(angle) }

fun LogoProg.apply(turtle: Turtle) { for(x in logos) x.apply(turtle) }

fun LogoRepeat.apply(turtle: Turtle) { for(x in 1..number) body.apply(turtle) }

fun Logo.apply(turtle: Turtle) {}


fun main()
{
    var t = Turtle()
    logo().fd(50).main().apply(t)
    println(t)

    t = Turtle()
    logo().fd(10).rt(90).fd(20).main().apply(t)
    println(t)

    t = Turtle()
    logo().repeat(6).fd(5).repeat(5).fd(10).fini().rt(90).main().apply(t)

    println(t)
}
