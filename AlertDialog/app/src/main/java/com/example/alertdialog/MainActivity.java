package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirDialog(View view){




        //  1 instanciar alertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder( this );

        // 2 Configurar titulo e mensagem
        dialog.setTitle("Termos de uso");
        dialog.setMessage("Voce concorda com os termos de uso?");

        // cofigurar cancelamento ( só fecha a tela se usuario escolher entre opções)

        dialog. setCancelable(false);

        // configurar ícone

        dialog.setIcon(android.R.drawable.ic_btn_speak_now);

        // Configurar ações para sim e nao

        dialog.setPositiveButton("Aceito.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(
                                getApplicationContext(),
                                "Condições aceitas!",
                                Toast.LENGTH_LONG
                        ).show();
                    }
        });

        dialog.setNegativeButton("Não aceito.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(
                        getApplicationContext(),
                        "Condições não aceitas!",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        // Criar e exibr alertDialog

        dialog.create();
        dialog.show();

    }
}