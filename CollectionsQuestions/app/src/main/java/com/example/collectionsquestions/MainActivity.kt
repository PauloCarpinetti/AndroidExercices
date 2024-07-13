package com.example.collectionsquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.collectionsquestions.database.DataBaseHelper
import com.example.collectionsquestions.database.ProdutoDAO
import com.example.collectionsquestions.databinding.ActivityMainBinding
import com.example.collectionsquestions.model.Produto

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val produtoDAO = ProdutoDAO(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonExecutar.setOnClickListener { cliqueBotao() }

        with(binding){
            buttonSalvar.setOnClickListener { salvar() }
            buttonListar.setOnClickListener { listar() }
            buttonDeletar.setOnClickListener { deletar() }
            buttonAtualizar.setOnClickListener { atualizar() }
        }

    }

    private fun salvar() {
        val produto = Produto(
            -1,
            "Notebook",
            "Accer"
        )
        produtoDAO.salvar(produto)
    }

    private fun listar() {
        val listaProdutos = produtoDAO.listar()
        if ( listaProdutos .isEmpty() ) {
            Log.i("info_db:", "Lista Vazia")
        }
    }

    private fun deletar() {
        val produto = Produto(
            0,
            "",
            ""
        )
        produtoDAO.remover(produto.idProduto)
    }

    private fun atualizar() {
        val produto = Produto(
            0,
            "Fone",
            "Bluetooh"
        )
        produtoDAO.atualizar(produto)
    }

    private fun cliqueBotao(){
        binding.textViewResultado.text = "Sucesso ao apertar o ..."
        Toast.makeText(
            this,
            "BOTÃO!",
            Toast.LENGTH_LONG
        ).show()
    }
}