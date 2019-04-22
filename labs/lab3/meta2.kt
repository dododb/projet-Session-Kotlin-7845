package lab3

import java.util.ArrayList
import java.io.IOException
import java.io.FileReader
import java.io.BufferedReader


fun readCSV(filename: String): ArrayList<String> {
    try {
        BufferedReader(FileReader(filename)).use { reader ->
            val list = ArrayList<String>()
            var line = reader.readLine()
            while (line != null) {
                val array = line
                list.add(array)
                line = reader.readLine()
            }
            return list
        }
    } catch (e: IOException) {
        e.printStackTrace()
        return ArrayList<String>()
    }

}

fun csvinit(filename: String) : Array<Any?> {
    val filenameSplit = filename.split("/")
    var className = filenameSplit.last().split(".")[0]
    className = className.substring(0, 1).toUpperCase() + className.substring(1)

    val list = readCSV(filename)

    val klass = Class.forName(className).kotlin
    val instances = Array(list.size-1) { i -> strinit(klass, list[i+1])}


    return instances
}


fun main(args: Array<String>)
{
    val ps = csvinit("/home/dododb/kotlin/labs/lab3/src/main/kotlin/personne.csv").map { it as Personne }
    for(pers in ps)
    {
        println(pers)
    }

}
