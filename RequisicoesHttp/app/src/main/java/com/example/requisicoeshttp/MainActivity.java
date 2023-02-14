package com.example.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView textoResultado;
    private Button botaoRecupera;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private StringBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoResultado = findViewById(R.id.textResultado);
        botaoRecupera = findViewById(R.id.buttonRecuperar);

        botaoRecupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask task = new MyTask();
                String urlApi = "https://blockchain.info/ticker";
                String moeda = "BRL";
                String urlApiMoeda = "https://blockchain.info/tobtc?currency=" + moeda + "&value=500";
                String cep = "01332020";
                String urlCep = "https://viacep.com.br/ws/" + cep + "/json/";
                task.execute( urlApi );
            }
        });

    }

    class MyTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            buffer = new StringBuffer();

            try {
                URL url = new URL( stringUrl );
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                // Recupera os dados em bytes
                inputStream = conexao.getInputStream();
                // Le os dados em bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader( inputStream );
                // converte para string
                BufferedReader reader = new BufferedReader( inputStreamReader );
                String linha = "";
                while ((linha = reader.readLine()) != null){
                    buffer.append( linha );
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute( resultado );

            /*
            String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String uf = null;*/

            String valorMoeda = null;
            String simbolo;
            String objetoValor;

            try {


                /*
                JSONObject jsonObject = new JSONObject( resultado );
                logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");

                 */
                JSONObject jsonObject = new JSONObject( resultado );
                objetoValor = jsonObject.getString("BRL");
                JSONObject jsonObjectReal = new JSONObject( objetoValor );
                valorMoeda = jsonObjectReal.getString("last");
                simbolo = jsonObjectReal.getString("symbol");

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //textoResultado.setText( resultado );
            //textoResultado.setText(logradouro+" / "+cep+" / "+bairro+" / "+localidade+" / "+uf);
            textoResultado.setText( simbolo + " " + valorMoeda );

        }
    }

}