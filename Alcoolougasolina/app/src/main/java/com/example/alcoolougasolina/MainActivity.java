package com.example.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrecoAlcool = findViewById(R.id.idPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.idPrecoGasolina);

        textResultado = findViewById(R.id.textResultado);

    }

    public void calcularPreco(View view){

        //recuperar dados digitados
        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        //validar campos digitados
        Boolean camposValidados = validarCampos(precoAlcool, precoGasolina);

        if ( camposValidados ) {

            // convertendo string para numeros
            Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);

            /* calculo (valorAlcool / valorGasolina) >= 0.7 melhor gasolina
            senao melhor alcool  */

            Double resultado = (valorAlcool/valorGasolina);
            if ( resultado >= 0.7 ){
                textResultado.setText("Melhor utilizar gasolina.");
            }else {
                textResultado.setText("Melhor utilizar alcool.");
            }

        }else {
            textResultado.setText("Preencha os campos primeiro!");
        }

    }

    public Boolean validarCampos(String pAlcool, String pGasolina){

        Boolean camposValidados = true;


        if( pAlcool == null || pAlcool.equals("")){
            camposValidados = false;

        }else if (pGasolina == null || pGasolina.equals("")){
            camposValidados = false;

        }

        return camposValidados;

    }
}
