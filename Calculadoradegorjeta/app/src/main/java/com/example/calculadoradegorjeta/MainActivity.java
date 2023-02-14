package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem;
    private TextView textGorjeta;
    private TextView textTotal;
    private TextView textGorjetaFinal;
    private TextView textPrecoFinal;
    private SeekBar seekBargorjeta;
    private double porcentagem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.mostraPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekBargorjeta = findViewById(R.id.seekBarGorjeta);
        textGorjetaFinal = findViewById(R.id.textGorjetaFinal);
        textPrecoFinal = findViewById(R.id.textPrecoFinal);

        // Listener seekBar

        seekBargorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                porcentagem = progress;
                textPorcentagem.setText( Math.round( porcentagem ) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){

        String valorRecuperado = editValor.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro",
                    Toast.LENGTH_LONG
            ).show();
        }else {
            double valorDigitado = Double.parseDouble(valorRecuperado);

            double gorjeta = valorDigitado * (porcentagem / 100);
            textGorjetaFinal.setText("R$ " + gorjeta);

            double precoFinal =  valorDigitado + gorjeta;
            textPrecoFinal.setText("R$ " + precoFinal);

        }
    }

}