package com.example.collectionsquestions

fun main() {
    val listaPerguntas = arrayListOf(
        Pergunta("bvdwidwf", 2),
        Pergunta("fbdfdfhhdff", 0),
    )

    val novaListaPerguntas = listaPerguntas.plus(Pergunta("nnsjdhdiccc", 3))
    listaPerguntas.forEach { item ->
        println( item )
        println( "listaPerguntas" )
    }

    novaListaPerguntas.forEach { item ->
        println( item )
        println( "NovaListaPerguntas" )
    }

    println(novaListaPerguntas.size)
    println( novaListaPerguntas.first())
    println( novaListaPerguntas.last())
    println( novaListaPerguntas[1])
    println( novaListaPerguntas.indexOf(Pergunta("bvdwidwf", 2)))

}