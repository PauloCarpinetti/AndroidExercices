package com.example.collectionsquestions

fun main() {

    val listaFrutas = mutableListOf("maça", "laranja", "banana")
    val listaVendas = mutableListOf(100, 200, 300, 400, 500)

    val novaListaFrutas = listaFrutas.filter { fruta ->
        fruta == "laranja"
    }

    val novaListaFrutas2 = listaFrutas.contains("maça")

    val novaListaVendas = listaVendas.filter { venda ->
        venda >= 200
    }
    val novaListaVendas2 = listaVendas.filter { it >= 300 }

    println( novaListaFrutas )
    println( novaListaVendas )
    println( novaListaVendas2 )
    println( novaListaFrutas2 )
}