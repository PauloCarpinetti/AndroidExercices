package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //metodos para criação de menus

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // metodo requer como parametro um arquivo xml de menus
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //metodo para cicks


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch( item.getItemId() ) {
            case R.id.itemSalvar:
                Toast.makeText(MainActivity.this,
                        "Item salvar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemEditar:
                Toast.makeText(MainActivity.this,
                        "Item Editar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemConfiguracoes:
                Toast.makeText(MainActivity.this,
                        "Item Configurações", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}