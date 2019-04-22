package lab3
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.primaryConstructor


fun getClassFromString(className: String) : KClass<Any>
{
    when (className) {
        "annee" -> {
            return Annee::class as KClass<Any>
        }
        "nom" -> {
            return String::class as KClass<Any>
        }
        "decisive" -> {
            return Boolean::class as KClass<Any>
        }
    }
    return Int::class as KClass<Any>
}

fun getFunctionSetName(str: String): String
{
    when (str) {
        "annee" -> {
            return "SetAnnee"
        }
        "nom" -> {
            return return "SetNom"
        }
        "decisive" -> {
            return return "SetDecisive"
        }
    }
    return ""
}

fun valueConverter(value: String, typeToConvert: KClass<Any>) : Any? {
    if (typeToConvert == Boolean::class)
        return java.lang.Boolean.parseBoolean(value)
    else if (typeToConvert == String::class) return value
    else {
        return strinit(typeToConvert, value)
    }
}
fun getInstance(klass: KClass<out Any>, setNames: Array<String>, values: String, valueType: Array<KClass<Any>>) : Any? {

    val valuesSplit = values.split(",")
    val instance = klass.primaryConstructor?.call()
    for((i,value) in valuesSplit.withIndex()) {

        val valueConverted = valueConverter(value, valueType[i])

        val setter = (klass.declaredMemberFunctions.filter { it.name == setNames[i] }).first()
        setter.call(instance, valueConverted)
    }
    return instance
}

fun csvinit2(filename: String): Array<Any> {
    val list = readCSV(filename)

    val filenameSplit = filename.split("/").toTypedArray()
    var className = filenameSplit.last().split(".").dropLastWhile { it.isEmpty() }.toTypedArray()[0]
    className = className.substring(0, 1).toUpperCase() + className.substring(1)

    val klass = Class.forName(className).kotlin


    val firstLine = list[0].split(",")
    val mapType = Array(firstLine.size) { getClassFromString(firstLine[it]) }
    val mapCol = Array(firstLine.size) { getFunctionSetName(firstLine[it]) }

    val lst = list.filter { it != list[0] }
    val instances = Array(list.size - 1)
    {
        getInstance(klass, mapCol, lst[it], mapType)
    } as Array<Any>


    return instances
}

fun main(args: Array<String>) {
    val bs = csvinit2("/home/dododb/kotlin/labs/lab3/src/main/kotlin/bataille.csv").map { it as Bataille }
    println(bs)
    for(bat in bs) {
        System.out.println(bat.toString())
        System.out.println(bat?.annee.toString())
    }
}
