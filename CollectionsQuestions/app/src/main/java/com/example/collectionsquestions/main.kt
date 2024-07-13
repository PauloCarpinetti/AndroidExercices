package com.example.collectionsquestions

fun main(){

    val pessoa = Pessoa()
    //pessoa.nome = "Paulo"
    //println("nome: ${pessoa.nome}")
    pessoa.salvarTelefones("(11) 98765 4321",
                                    "(11) 98765 4321",
                                    "(11) 98765 4321",
                                    "(11) 98765 4321",
                                    "(11) 98765 4321")

 /*   val usuario = Usuario()
    usuario.nome = "Paulo"
    usuario.idade = 40

    println("Usuario: ${usuario.nome} - idade: ${usuario.idade} - maiorIdade: ${usuario.maiorIdade}")*/

    val usuario = Usuario()
    usuario.salvartelefones("(11) 98765 4321",
                                    "(11) 98765 4321",
                                    "(11) 98765 4321",
                                    "(11) 98765 4321",
                                    "(11) 98765 4321")

    println( listaPerguntas().first() )
    println( listaPerguntas().random() )

    var perguntaX: Pergunta = listaPerguntas().first()
    //listaPerguntasUsadas().add(perguntaX)

    listaPerguntas().add(perguntaX)
    listaPerguntas().drop(0)
    println(listaPerguntas())
}
