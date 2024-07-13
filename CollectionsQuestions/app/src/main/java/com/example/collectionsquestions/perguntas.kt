package com.example.collectionsquestions

data class Pergunta(val pergunta: String, val respostaCerta: Int ){

}

fun main() {

    val pergunta1 = Pergunta("Qual o meu nome?", 2)
    val pergunta2 = Pergunta("Qual o meu nome?", 1)

    // desestruturação

    val (pergunta, resposta) = pergunta1

    println( pergunta )
    println( resposta )


    /*println( pergunta1 ) //Pergunta@266474c2
    println( pergunta2 ) //Pergunta@6f94fa3e
    println( pergunta1 == pergunta2 )*/

}