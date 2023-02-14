package com.example.cardview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cardview.R;
import com.example.cardview.adapter.PostagemAdapter;
import com.example.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recylcerPostagem;
    private List<Postagem> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recylcerPostagem = findViewById(R.id.recyclerPostagem);

        // Definir Layout

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recylcerPostagem.setLayoutManager( layoutManager );

        // Definir adapter
        this.prepararPostagens();
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recylcerPostagem.setAdapter( adapter );


    }

    public void prepararPostagens(){

        Postagem p = new Postagem("Livia", "viagem legal", R.drawable.imagem4);
        this.postagens.add(p);

        p = new Postagem("Livia","viagem legal", R.drawable.imagem4);
        this.postagens.add(p);

        p = new Postagem("Livia","viagem legal", R.drawable.imagem4);
        this.postagens.add(p);

        p = new Postagem("Livia", "viagem legal", R.drawable.imagem4);
        this.postagens.add(p);

        p = new Postagem("Livia", "viagem legal", R.drawable.imagem4);
        this.postagens.add(p);
    }
}