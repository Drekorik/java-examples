package org.fireplume.extentions

class Extensions {
}

fun main() {
    val someClass = Extensions()
    someClass.newFunction()

    val intToString: Extensions.(Int) -> String =
            { i -> "This is extended function too, and int is ${i.toString()}" }
    val res1 = someClass.intToString(5)
    println(res1)
}

fun Extensions.newFunction() {
    println("This is extended function")
}

