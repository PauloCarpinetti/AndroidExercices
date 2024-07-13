package com.example.collectionsquestions.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.collectionsquestions.model.Produto

class ProdutoDAO(context: Context): IProdutoDAO {

    private val escrita = DataBaseHelper(context).writableDatabase
    private val leitura = DataBaseHelper(context).readableDatabase

    override fun salvar(produto: Produto): Boolean {

        val titulo = produto.titulo
        val desccricao = produto.descricao

        val sql = "INSERT INTO produtos VALUES(null, '$titulo', '$desccricao');"
        try {
            escrita.execSQL(sql)
            Log.i("info_db:", "Sucesso ao inserir dados db")
        } catch (e: Exception) {
            Log.i("info_db:", "Erro ao inserir dados db")
            return false
        }
        return true
    }

    override fun atualizar(produto: Produto): Boolean {

        val idProduto = produto.idProduto
        val titulo = produto.titulo

        val sql = "UPDATE ${DataBaseHelper.TABELA_PRODUTOS} " +
                "SET ${DataBaseHelper.TITULO} = `$titulo` " +
                "WHERE ${DataBaseHelper.ID_PRODUTO} = $idProduto;"
        try {
            escrita.execSQL( sql )
            Log.i("info_db:", "Sucesso ao atualizar dados db" )
        } catch (e: Exception ) {
            Log.i("info_db:", "Erro ao atualizar dados db" )
            return false
        }
        return true
    }

    override fun remover(idProduto: Int): Boolean {

        val idProduto = idProduto

        val sql = "DELETE FROM ${DataBaseHelper.TABELA_PRODUTOS} " +
                "WHERE ${DataBaseHelper.ID_PRODUTO} = $idProduto;"

        try {
            escrita.execSQL(sql)
            Log.i("info_db:", "Sucesso ao deletar dados db" )
            } catch (e: Exception) {
            Log.i("info_db:", "Sucesso ao deletar dados db" )
            return false
        }
        return true
    }

    override fun listar(): List<Produto> {

        val listaProdutos = mutableListOf<Produto>()

        val sql = "SELECT * FROM produtos;"
        val cursor = leitura
            .rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex("${DataBaseHelper.ID_PRODUTO}")
        val indiceTitulo = cursor.getColumnIndex("${DataBaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DataBaseHelper.DESCRICAO}")

        while ( cursor.moveToNext() ) {
            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)

            val produto = Produto(idProduto, titulo, descricao)
            listaProdutos.add(produto)
            Log.i("info_db:", "id: $idProduto - $titulo - $descricao")
        }
        Log.i("info_db:", "Listou os produtos")
        return listaProdutos
    }


}