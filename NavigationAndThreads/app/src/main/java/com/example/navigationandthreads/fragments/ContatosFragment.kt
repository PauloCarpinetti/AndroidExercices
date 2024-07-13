package com.example.navigationandthreads.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationandthreads.R

class ContatosFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("info","on Attach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contatos,
            container,
            false
        )
    }
}