package com.example.bdsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            // criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas ( nome VARCHAR, idade INT(3) )");
            // inserir dados
            //bancoDados.execSQL("INSERT INTO pessoas(nome ,idade) VALUES('Paulo', 40)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome ,idade) VALUES('Lívia', 36)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome ,idade) VALUES('Roberto', 56)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome ,idade) VALUES('Amanda', 18)");
            bancoDados.execSQL("UPDATE pessoas SET idade = 39 WHERE nome = 'Paulo'");

            // recuperar pessoas
           /* String consulta = "SELECT nome, idade" +
                              " FROM pessoas WHERE nome = 'Paulo'" +
                              " AND idade < 50 ";*/

            String consulta =  "SELECT nome, idade FROM pessoas" +
                               " WHERE nome IN ('Paulo', 'Lívia')";
            // filtros where, in, between, like, order by
            Cursor cursor = bancoDados.rawQuery( consulta , null);
            //indices da tabela


            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while( cursor != null){

                String nome = cursor.getString( indiceNome );
                String idade = cursor.getString( indiceIdade );

                Log.i("Resultado - nome ", nome + " / idade: " + idade);

                cursor.moveToNext();
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}