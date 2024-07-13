package com.example.collectionsquestions

fun main() {

    val map = mapOf(102100 to "Notebook", 102101 to "Celular")

    map.forEach { produto ->
        println( " ${produto.key} - ${produto.value} ")
    }

    val mutableMap = mutableMapOf(102100 to "Notebook", 102101 to "Celular", 102102 to "TV-Samsung")

    mutableMap[102103] = "PlayStation 5" // mutableMap.put
    val newMutableMap = mutableMap.plus(102104 to "XBOX series X") // cria nova mutableMap e inclui

    mutableMap.forEach { produto ->
        println( " ${produto.key} - ${produto.value} ")
    }

    newMutableMap.forEach { produto ->
        println( " ${produto.key} - ${produto.value} " )
    }

    println(newMutableMap.size)


}