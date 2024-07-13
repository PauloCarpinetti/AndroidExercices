package com.example.permissoes


import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissoes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonGravar.setOnClickListener { onCaptureCliked()}
        binding.buttonParar.setOnClickListener { onClickedStop()}
    }

    private fun onCaptureCliked() {
        println("Começou")
        if (!isRecordAudioPermissionGranted()) {
            reqestRecordAudioPermission()
        } else {
            println("Start Recording")
        }
    }

    private fun onClickedStop() {
        println("Parou")
    }

    private fun isRecordAudioPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun reqestRecordAudioPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            RECORD_AUDIO_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE){
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permissão concedida!",
                    Toast.LENGTH_LONG).show()
                // mostrar popup
            } else {
                Toast.makeText(this,"Permissão negada!",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val RECORD_AUDIO_PERMISSION_CODE = 1000
    }

}