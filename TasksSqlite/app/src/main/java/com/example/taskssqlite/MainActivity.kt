package com.example.taskssqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskssqlite.adapter.TarefaAdapter
import com.example.taskssqlite.databinding.ActivityMainBinding
import com.example.taskssqlite.model.Tarefa
import com.example.taskssqlite.model.TarefaDAO

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listaTarefas = emptyList<Tarefa>()
    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefa::class.java)
            startActivity( intent )
        }

        //RecyclerView
        tarefaAdapter = TarefaAdapter(
            { id -> confirmarExclusao(id) },
            { tarefa -> editar(tarefa)}
        )
        binding.rvTarefas.adapter = tarefaAdapter
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)


    }

    private fun editar(tarefa: Tarefa) {

        val intent = Intent(this, AdicionarTarefa::class.java)
        intent.putExtra("tarefa", tarefa)
        startActivity( intent )
    }

    private fun confirmarExclusao(id: Int) {
        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")

        alertBuilder.setPositiveButton("Sim"){ _, _ ->
            val tarefaDAO = TarefaDAO(this)
            if ( tarefaDAO.remover( id ) ){
                atualizarListaTarefas()
                Toast.makeText(this,
                    "Sucesso ao remover tarefa.",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,
                    "Erro ao remover tarefa.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        alertBuilder.setNegativeButton("Não"){ _, _ -> }

        alertBuilder.create().show()
    }

    private fun atualizarListaTarefas(){
        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()
        tarefaAdapter?.adicionarLista( listaTarefas )
    }

    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
        listaTarefas.forEach { tarefa->
            Log.i("info_db", "${tarefa.descricao} \n")
        }
    }


}