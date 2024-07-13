package com.example.collectionsquestions

class Mensagem(
    private val mensagem: String,
    private val duracao: Int
) {

    companion object {

        const val DURACAO_CURTA = 0
        const val DURACAO_LONGA = 1

        fun construirTexto( mensagem: String, duracao: Int): Mensagem {
            return Mensagem(mensagem, duracao)
        }
    }

    fun exibir(){
        println("M: ${this.mensagem} - ${this.duracao}s")
    }
}


fun main(){
        Mensagem.construirTexto(
            "Olá",
            Mensagem.DURACAO_LONGA
        ).exibir()


}