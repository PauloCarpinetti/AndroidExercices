package com.example.taskssqlite.model

interface ITarefaDAO {

    fun salvar( tarefa: Tarefa): Boolean
    fun atualizar( tarefa: Tarefa): Boolean
    fun remover( idtarefa: Int): Boolean
    fun listar(): List<Tarefa>

}