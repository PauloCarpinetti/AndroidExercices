package com.example.navigationandthreads.activities



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.navigationandthreads.Usuario
import com.example.navigationandthreads.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Runnable

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var pararThread = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAbrirTela.setOnClickListener{ abrirActivityComFragments() }
        binding.buttonIniciar.setOnClickListener{ iniciarCorroutine() }
        binding.buttonParar.setOnClickListener{ pararThread()}
    }

    private fun iniciarCorroutine(){
        CoroutineScope( Dispatchers.IO ).launch {
            /*val executou: String = getString(R.string.executou)
            binding.buttonIniciar.text = executou*/

           /* repeat(30){indice ->
                Log.i("info_thread", "Executando: $indice T:${Thread.currentThread().name}")
                withContext(Dispatchers.Main){
                    binding.buttonIniciar.text = "executando $indice"
                }
                delay(1000)

            }*/
           executarSuspensa()


        }
    }

    private suspend fun executarSuspensa(){
        val usuario = recuperarUsuarioLogado()
        Log.i("info", "Usuario: ${usuario.nome} ${Thread.currentThread().name}")
        val postagens = recuperarPostagemId( usuario.id )
        Log.i("info", "Postagens: ${postagens.size} ${Thread.currentThread().name}")
    }

    private suspend fun recuperarPostagemId(idUsuario: Int): List<String> {
        delay(2000)
        return listOf(
            "Estudando física",
            "Estudando Android"
        )
    }

    private suspend fun recuperarUsuarioLogado(): Usuario {
        delay(2000)
        return Usuario(1020, "Paulo Carpinetti")
        Log.i("info", "Recuperou Usuario!${Thread.currentThread().name}")
    }

    private fun pararThread(){
        pararThread = true
        iniciarBotao()
    }

    private fun iniciarThread() {
        //MinhaThread().start()
        Thread( MinhaRunnable() ).start()
    }

    private fun iniciarBotao(){
        binding.buttonIniciar.text = "Reiniciar execução"
        binding.buttonIniciar.isEnabled = true
    }

    private fun abrirActivityComFragments() {
        val intent = Intent(this, SegundaActivity::class.java)
        startActivity( intent )
        Log.i("info", "Thread:${Thread.currentThread().name}")

    }

    inner class MinhaRunnable : Runnable {
        override fun run() {

            if (pararThread) {
                pararThread = false
                return
            }

            repeat(30){indice ->
                Log.i("info_thread", "Executando: $indice T:${Thread.currentThread().name}")
                Thread.sleep(1000)
                runOnUiThread{
                    binding.buttonIniciar.text = "executando $indice"
                    binding.buttonIniciar.isEnabled = false
                    if ( indice == 29){
                        iniciarBotao()
                    }
                }
                Thread.sleep( 1000 )
            }
        }

    }
    inner class MinhaThread : Thread() {

        override fun run() {
            super.run()

            if (pararThread)return

            repeat(30){indice ->
                Log.i("info_thread", "Executando: $indice T:${currentThread().name}")
                sleep(1000)
                runOnUiThread{
                    binding.buttonIniciar.text = "executando $indice"
                    binding.buttonIniciar.isEnabled = false
                    if ( indice == 29){
                        iniciarBotao()
                    }
                }
                sleep( 1000 )
            }

        }
    }


}