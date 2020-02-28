package org.fireplume.hw

class HelloWorldApp {

    companion object {
        fun staticFun() = println("I'm static")
    }

    private fun privateFun() = println("I'm private")

    protected fun protectedFun() = println("I'm protected")

    internal fun internalJavaFun() = println("I'm package private (internal)")

    fun publicFun() {
        println("I'm public")
        privateFun()
        protectedFun()
        internalJavaFun()
        staticFun()
    }
}

fun main(args: Array<String>) {
    println("Hello world")
    HelloWorldApp().publicFun()
}

