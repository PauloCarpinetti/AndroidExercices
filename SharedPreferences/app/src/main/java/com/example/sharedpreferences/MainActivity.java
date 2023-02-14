package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText inputEditTextNome;
    private TextView textResultado;
    private final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        inputEditTextNome = findViewById(R.id.inputTextNome);
        textResultado = findViewById(R.id.textResultado);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //validar o nome
                if(inputEditTextNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome!",
                            Toast.LENGTH_LONG).show();
                } else {
                    String nome = inputEditTextNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textResultado.setText("Olá, " + nome);
                }

            }
        });

        //recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        // valida se temos o nnome em preferencias
        if ( preferences.contains("nome") ){
            String nome = preferences.getString("nome", "Olá, usuário não definido.");
            textResultado.setText("Olá! " + nome);
        } else {
            textResultado.setText("Olá, usuário não definido.");
        }
    }
}