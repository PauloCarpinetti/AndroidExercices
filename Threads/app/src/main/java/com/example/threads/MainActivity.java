package com.example.threads;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoIniciar;
    private Button botaaoParar;
    private int numero;
    private Handler handler = new Handler();
    private Boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = findViewById(R.id.buttonIniciar);
        botaaoParar = findViewById(R.id.buttonParar);

    }

    public void iniciarThread(View view) throws InterruptedException {

        /*MyThread thread = new MyThread();
        thread.start();
         */
        pararExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread( runnable ).start();

        /*
        Sobrecarrega a UI Thread
        for (int i=0; i<=15; i++){
            Log.d("thread", "contador: " + i );
            try {
                 Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        } */
    }

    public void pararThread(View view){

        pararExecucao = true;
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            // cria nova Thread e não sobrecarrega a UI Thread
            for (int i=0; i<=15; i++){
                Log.d("thread", "contador: " + i );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
           /* for (int i=0; i<=15; i++){
                numero = i;
                Log.d("thread", "contador: " + i );
                //botaaoIniciar.setText("Contador: " + i );

            */
                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        botaaoIniciar.setText("Contador: " + numero );
                    }
                });
                 */

            for (int i = 0; i <= 15; i++) {
                if (pararExecucao){
                    return;
                }
                numero = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("Contador: " + numero);
                    }
                });

            }
        }

    }
}
