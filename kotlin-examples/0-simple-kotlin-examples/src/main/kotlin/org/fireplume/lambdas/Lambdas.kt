package org.fireplume.lambdas

import java.util.function.Consumer
import java.util.function.Function

class Lambdas {
    fun doSmth() {
        val c = Consumer { any: Any -> println(any.toString()) }
        consumer(c, "This is consumer")

        val n: (Any) -> Unit = { any: Any -> println(any.toString()) }
        noReturn(n, "This is no return function")

        val f = Function { any: Any -> any.toString() }
        function(f, "This is function")

        val l: (Any) -> String = { any: Any -> any.toString() }
        lambda(l, "This is any lambda")
    }

    private fun consumer(consumer: Consumer<Any>, any: Any) {
        consumer.accept(any)
    }

    private fun noReturn(noReturn: (Any) -> Unit, any: Any) {
        noReturn.invoke(any)
    }

    private fun function(function: Function<Any, String>, any: Any) {
        val res = function.apply(any)
        println(res)
    }

    private fun lambda(l: (Any) -> String, any: Any) {
        val res = l.invoke(any)
        println(res)
    }
}

fun main() {
    Lambdas().doSmth()
}