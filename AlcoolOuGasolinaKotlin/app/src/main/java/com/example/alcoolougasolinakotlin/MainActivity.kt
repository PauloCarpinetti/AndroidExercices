package com.example.alcoolougasolinakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.alcoolougasolinakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonCalcular.setOnClickListener { calcularPreco() }
    }

    private fun calcularPreco() {
        var precoAlcool = findViewById<EditText>(R.id.editTextAlcool).toString()
        var precoGasolina = findViewById<EditText>(R.id.editTextGasolina).toString()

        val validaCampos = validarCampos(precoAlcool, precoGasolina)

        if ( validaCampos ) {
            calcularMelhorPreco( precoAlcool, precoGasolina)
        } else binding.textViewResultado.text = "Preencha os dados primeiro!"

    }

    private fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {

        var camposValidados: Boolean = true

        if ( precoAlcool== null || precoAlcool.equals("") ) {
            camposValidados = false
        } else if (precoGasolina== null || precoGasolina.equals("")){
            camposValidados = false
        }

        return camposValidados
    }

    private fun calcularMelhorPreco(precoAlcool: String, precoGasolina: String) {

        val valorAlcool = precoAlcool.toDouble()
        val valorGasolina = precoGasolina.toDouble()

        /*
            (precoAlcool / precoGasolina) se resultado for
            maior 0.7 melhor gasolina se nao alcool
         */

        var resultado = valorAlcool / valorGasolina

        if ( resultado >= 0.7) {
            binding.textViewResultado.text = "Melhor usar Alcool!"
        } else {
            binding.textViewResultado.text = "melhor usar Gasolina!"
        }
    }


}