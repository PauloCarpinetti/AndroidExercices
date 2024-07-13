package com.example.collectionsquestions

class Usuario() {

    var nome: String = ""
        get() {
            println("get: $field")
            return field.uppercase() // Campo  getter and setter sem os println sao redundantes ja tem automatico
        }
        set(value) {
            println("set: $field")
            field = value
        }
    var idade: Int = 0
    val maiorIdade
        get() = idade >= 18

    fun salvartelefones(vararg telefones: String ){
        for ( telefone in telefones ) {
            println("Telefone: $telefone KOTLINLANG")
        }
    }
}