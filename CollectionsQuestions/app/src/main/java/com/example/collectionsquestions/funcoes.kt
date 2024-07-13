package com.example.collectionsquestions

class Matematica {
    fun somar(n1: Int, n2: Int): Int {
        return n1 + n2
    }
}

fun somar(n1: Int, n2: Int): Int {
    return n1 + n2
}

fun listaPerguntas(): MutableList<Pergunta> {
    return mutableListOf(
        Pergunta("bvdwidwf", 0),
        Pergunta("fbdfdfhhdff", 1),
        Pergunta("bvdwidwf", 2),
        Pergunta("fbdfdfhhdff", 3),
        Pergunta("bvdwidwf", 4),
        Pergunta("fbdfdfhhdff", 5),
        Pergunta("bvdwidwf", 6),
        Pergunta("fbdfdfhhdff", 7),
    )
}

fun listaPerguntasUsadas(): MutableList<Pergunta> = mutableListOf()

fun calcular( funcao: (Int, Int)-> Int ) {
    val retorno = funcao(10, 40)
    println( retorno )
}

fun executar(){
    println("Executar")
}

fun executar2() = println("Executar")

// função lambda

fun main() {

    val funcaoLambda = {
        println("Função Lambda!")
    }

    val funcaoLambda2 = { nome: String ->
        println("Bom dia $nome!")
    }

    executar()
    funcaoLambda()
    funcaoLambda2("Paulo")

    /*val matematica1 = Matematica()
    println(matematica1.somar(10, 20 ))
    calcular( ::somar )
    val matematica2 = Matematica()
    calcular( matematica2::somar )*/


}