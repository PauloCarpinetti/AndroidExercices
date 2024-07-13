package com.example.collectionsquestions.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context): SQLiteOpenHelper(
    // 1) Contexto
    // 2) Nome do banco de dados
    // 3) CursorFsctory
    // 4) versão do banco de dados
    context, "loja.db", null, 1
) {
    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"
    }
    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id_produto integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "titulo varchar(100)," +
                "descricao text" +
                ");"
        try {
            db?.execSQL( sql )
            Log.i("info_db:", "Sucesso ao instalar db" )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db:", "Sucesso ao instalar db" )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("info_db:", "Sucesso ao fazer upgrade do db" )
    }
}