package com.example.taskssqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskssqlite.databinding.ActivityAdicionarTarefaBinding
import com.example.taskssqlite.model.Tarefa
import com.example.taskssqlite.model.TarefaDAO

class AdicionarTarefa : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarTarefaBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarTarefaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Recuperar tarefa passada da outra activity
        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if ( bundle != null ) {
            tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText( tarefa.descricao )
        }

        binding.btnSalvar.setOnClickListener {

            if ( binding.editTarefa.text.isNotEmpty()){
                if ( tarefa != null ){
                    editar( tarefa )
                } else {
                    salvar()
                }
            }else {
                Toast.makeText(this,
                    "Preencha uma tarefa",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun editar(tarefa: Tarefa) {
        val descricao = binding.editTarefa.text.toString()
        val tarefaAtualizar = Tarefa(
            tarefa.idTarefa,
            descricao,
            "default"
        )
        val tarefaDAO = TarefaDAO(this)

        if (tarefaDAO.atualizar( tarefaAtualizar )) {
            Toast.makeText(
                this,
                "Tarefa atualizada com sucesso!",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    private fun salvar() {
        val descricao = binding.editTarefa.text.toString()
        val tarefa = Tarefa(
            -1,
            descricao,
            "default"
        )
        val tarefaDAO = TarefaDAO(this)
        if (tarefaDAO.salvar(tarefa)) {
            Toast.makeText(
                this,
                "Tarefa salva com sucesso!",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }


}