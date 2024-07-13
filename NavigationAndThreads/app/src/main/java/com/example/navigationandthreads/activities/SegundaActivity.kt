package com.example.navigationandthreads.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.navigationandthreads.R
import com.example.navigationandthreads.databinding.ActivitySegundaBinding
import com.example.navigationandthreads.fragments.ContatosFragment
import com.example.navigationandthreads.fragments.ConversasFragment

class SegundaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySegundaBinding.inflate( layoutInflater )
    }
    private var categoria: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*val fragmentManager = supportFragmentManager.beginTransaction()

        fragmentManager.add(R.id.fragmentConteudo, ConversasFragment() )

        fragmentManager.commit()*/



        binding.buttonContatos.setOnClickListener{ abrirContatos() }
        binding.buttonConversas.setOnClickListener{ abrirConversas() }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentConteudo, ConversasFragment())
            .commit()
    }

    private fun abrirConversas() {
       /* supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentConteudo, ConversasFragment())
            .commit()*/


    }

    private fun abrirContatos() {
        /*supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentConteudo, ContatosFragment())
            .commit()*/

        // mesmo com fragment ktx (Jetpack)

        supportFragmentManager.commit {
            var bundle?: String = null
            replace<ContatosFragment>(R.id.fragmentConteudo, args = bundle)
        }
    }
}