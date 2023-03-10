package com.example.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_tarefas";
    public static String TABELA_TAREFAS = "tarefas";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL  ); ";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar tabela" );
        } catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


         String sql = " DROP TABLE IF EXISTS  " + TABELA_TAREFAS + " ;";

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "Sucesso ao atualizar app" );
        } catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar app" + e.getMessage());
        }

    }
}
