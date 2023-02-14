package com.example.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private EditText campoNome;
    private TextInputEditText campoEmail;
    private TextView textoResultado;
    private CheckBox checkEsporte, checkMusica, checkEducacao;
    private RadioButton sexoMasculino, sexoFeminino;
    private RadioGroup escolhaSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         campoNome = findViewById(R.id.editNome);
         campoEmail = findViewById(R.id.editEmail);
         textoResultado = findViewById(R.id.resultado);
         checkEsporte = findViewById(R.id.checkEsporte);
         checkMusica = findViewById(R.id.checkMusica);
         checkEducacao = findViewById(R.id.checkEducacao);
         sexoMasculino = findViewById(R.id.radioButtonM);
         sexoFeminino = findViewById(R.id.radioButtonF);
         escolhaSexo = findViewById(R.id.radioGroupSexo);
         radioButton();

    }

    public  void radioButton(){

        /*
        if(sexoMasculino.isChecked()){
            textoResultado.setText("Masculino");
        }else if(sexoFeminino.isChecked()){
            textoResultado.setText("Feminino");
        }
        */
        escolhaSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonM){
                    textoResultado.setText("Masculino");
                }else if(i == R.id.radioButtonF){
                    textoResultado.setText("Feminino");
                }
            }
        });
    }

    public void checkBox(){

        String texto = "";
        if(checkEsporte.isChecked()){
            texto = texto + "Esporte selecionado -";
        }
        if(checkMusica.isChecked()){
            texto = texto + "Musica selecionada -";
        }
        if(checkEducacao.isChecked()){
            texto = texto + "Educacao selecionada -";
        }

        textoResultado.setText(texto);

    }

    public void enviar(View view) {


        /*
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        textoResultado.setText("Nome: " + nome + " " + "email: " + email);
        */

        //checkBox();
        //radioButton();

    }
    public  void limpar(View view){

        campoNome.setText("");
        campoEmail.setText("");
        textoResultado.setText("");

    }
}