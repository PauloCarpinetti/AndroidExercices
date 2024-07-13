package com.example.collectionsquestions

fun main(){

val set = setOf("Paulo", "Roberto", "Ana", "Julia")

    set.forEach{ nome ->
        println(nome)
    }

val mutableSet = mutableSetOf("Ana", "Julia", "Paulo", "Carpinetti")

    mutableSet.add("Souza")

    mutableSet.forEach{ nome ->
        println(nome)
    }

}