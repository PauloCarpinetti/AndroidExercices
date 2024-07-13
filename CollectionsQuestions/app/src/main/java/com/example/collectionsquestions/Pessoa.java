package com.example.collectionsquestions;

import androidx.annotation.NonNull;

public class Pessoa {

    private String nome = "";
    private int idade = 40;

    public void salvarTelefones(@NonNull String... telefones) {
        //
        for (String telefone : telefones ){
            System.out.println("telefone: " + telefone + " JAVALANG");
        }
    }

    public String getNome() {
        System.out.println("get: " + nome);
        return nome;
    }

    public void setNome(String nome) {
        System.out.println("set: " + nome);
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
