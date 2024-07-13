package com.example.navigationandthreads

import android.util.Log

class MinhaThread : Thread() {

    override fun run() {
        super.run()
        repeat(30){indice ->
            Log.i("info_thread", "Executando: $indice T:${currentThread().name}")
            sleep(1000)

        }

    }
}