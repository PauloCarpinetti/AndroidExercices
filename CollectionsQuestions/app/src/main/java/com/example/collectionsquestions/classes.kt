package com.example.collectionsquestions

class Motorista(private val nome: String){
    fun exibirDadosMotorista() = println("Motorista: $nome")

    class Caminhao(private val nomeCaminhao: String){ // Classe aninhada (Nested Class)
        fun exibirDadosCaminhao() = println("Caminhao: $nomeCaminhao")
    }
}

class Motorista2(private val nome: String){
    fun exibirDadosMotorista2() = println("Motorista: $nome")

    inner class Caminhao2(private val nomeCaminhao: String){ // classe interna
        fun exibirDadosCaminhao2() = println("Caminhao: $nomeCaminhao motorista $nome")
    }
}

fun main(){

    val caminhao = Motorista.Caminhao("Scania TR2000")
    caminhao.exibirDadosCaminhao()

    val motorista2 = Motorista2("Paulo")
    val caminhao2 = motorista2.Caminhao2("Scania TR2000")
    caminhao2.exibirDadosCaminhao2()

    /*val motorista = Motorista("Paulo")
    motorista.exibirDadosMotorista()*/


}