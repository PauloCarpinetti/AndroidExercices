package com.example.notificacoes.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    public static boolean validarPermissoes(String [] permissoes, Activity activity, int requestCode){

        if (Build.VERSION.PREVIEW_SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<>();

            for ( String permissao : permissoes) {
                Boolean temPermissao = ContextCompat.checkSelfPermission(activity, permissao)
                        == PackageManager.PERMISSION_GRANTED;
                if (!temPermissao) listaPermissoes.add(permissao);
            }
            // se lista estiver vazia nao é necessario solicitar permissoes
            if ( listaPermissoes.isEmpty()) return true;

            String[] novasPermissoes = new String[ listaPermissoes.size() ];
            listaPermissoes.toArray( novasPermissoes );

            // solicta permissao
            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);

        }
        return true;
    }
}
