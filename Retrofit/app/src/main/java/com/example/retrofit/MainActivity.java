package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofit.api.CEPService;
import com.example.retrofit.api.DataService;
import com.example.retrofit.model.CEP;
import com.example.retrofit.model.Foto;
import com.example.retrofit.model.Postagem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoSolicitar;
    private TextView textoResultado;
    private Retrofit retrofit;
    private List<Foto> listaFotos = new ArrayList<>();
    private List<Postagem> listaPostagens = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoSolicitar = findViewById(R.id.buttonSolicitar);
        textoResultado = findViewById(R.id.textResultado);

        retrofit = new Retrofit.Builder()
                //.baseUrl("https://viacep.com.br/ws/")
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recuperarCEPRetrofit();
                //recuperarListaRetrofit();
                //recuperarListaPostagens();
                //salvarPostagem();
                //salvarPostagem2();
                //atualizarPostagemPut();
                //atualizarPostagemPatch();
                removerPostagem();
            }
        });
    }

    public void removerPostagem(){

        DataService service = retrofit.create(DataService.class);
        Call<Void> call = service.removerPostagem(2);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    textoResultado.setText( "Status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void atualizarPostagemPatch(){

        //Postagem postagem = new Postagem("1234", null, "Corpo postagem");
        Postagem postagem = new Postagem();
        postagem.setBody("Corpo da menssagem alterado!");

        DataService service = retrofit.create(DataService.class);
        Call<Postagem> call = service.atualizarPostagemPatch(2, postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()) {
                    Postagem postagemResposta = response.body();
                    textoResultado.setText(
                            "Status: " + response.code() +
                                    " Id: " + postagemResposta.getId() +
                                    " título: " + postagemResposta.getTitle() +
                                    " userId: " + postagemResposta.getUserId() +
                                    " body: " + postagemResposta.getBody()
                    );
                }
            }
            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    public void atualizarPostagemPut(){

        Postagem postagem = new Postagem("1234", null, "Corpo postagem");

        DataService service = retrofit.create(DataService.class);
        Call<Postagem> call = service.atualizarPostagemPut(2, postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if ( response.isSuccessful() ) {
                    Postagem postagemResposta = response.body();
                    textoResultado.setText(
                            "Status: " + response.code() +
                                    " Id: " + postagemResposta.getId() +
                                    " título: " + postagemResposta.getTitle() +
                                    " userId: " + postagemResposta.getUserId() +
                                    " body: " + postagemResposta.getBody()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void salvarPostagem2(){

        DataService service = retrofit.create(DataService.class);
        Call<Postagem> call = service.salvarPostagem2( "1234", "Titulo postagem", "Corpo postagem" );

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if ( response.isSuccessful() ) {
                    Postagem postagemResposta = response.body();
                    textoResultado.setText(
                            "Código: " + response.code() +
                                    " Id: " + postagemResposta.getId() +
                                    " título: " + postagemResposta.getTitle()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void salvarPostagem(){

        Postagem postagem = new Postagem("1234", "Titulo postagem", "Corpo postagem");

        DataService service = retrofit.create(DataService.class);
        Call<Postagem> call = service.salvarPostagem( postagem );

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if ( response.isSuccessful() ){
                    Postagem postagemResposta = response.body();
                    textoResultado.setText(
                            "Código: " + response.code() +
                            " Id: " + postagemResposta.getId() +
                            " título: " + postagemResposta.getTitle()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void recuperarListaPostagens(){

        DataService service = retrofit.create(DataService.class);
        Call<List<Postagem>> call = service.recuperarPostagens();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if ( response.isSuccessful() ) {
                    listaPostagens = response.body();
                    for ( int i = 0; i <listaPostagens.size(); i++){
                        Postagem postagem = listaPostagens.get( i );
                        Log.d("resultado", "resultado: " + postagem.getId() + " / " + postagem.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });
    }

    private void recuperarListaRetrofit(){

        DataService service = retrofit.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if (response.isSuccessful()) {
                    listaFotos = response.body();
                    for ( int i=0; i<listaFotos.size(); i++) {
                        Foto foto = listaFotos.get( i );
                        Log.d("resultado", "resultado: " + foto.getId() + " / " + foto.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }

    private void recuperarCEPRetrofit(){

        CEPService cepService = retrofit.create( CEPService.class );
        Call<CEP> call = cepService.recuperarCEP("01310100");

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if ( response.isSuccessful()){
                    CEP cep = response.body();
                    textoResultado.setText( cep.getLogradouro() + " / " + cep.getBairro() );
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}