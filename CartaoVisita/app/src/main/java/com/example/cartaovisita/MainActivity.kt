package com.example.cartaovisita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cartaovisita.ui.theme.CartaoVisitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartaoVisitaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LayoutFinalCartaoVisita()
                }
            }
        }
    }
}

@Composable
fun CardSuperior() {
    val image = painterResource(R.drawable.ic_launcher_foreground)
    Column (modifier = Modifier){
        Image(painter = image, contentDescription = null)
        Text(text = "Texto 1")
        Text(text = "Texto 2")
    }
}

@Composable
fun Line(imagePainter: Painter, descricao: String) {
    Row {
        Image(painter = imagePainter, contentDescription = null)
        Text(text = descricao)
    }
}

@Composable
fun CardInferior() {
    Column (modifier = Modifier){
        Line(imagePainter = painterResource(R.drawable.ic_launcher_foreground),
            descricao = stringResource(R.string.phone))
        Line(imagePainter = painterResource(R.drawable.ic_launcher_foreground),
            descricao = stringResource(R.string.social_media))
        Line(imagePainter = painterResource(R.drawable.ic_launcher_foreground),
            descricao = stringResource(R.string.email))

    }
}

@Composable
fun LayoutFinalCartaoVisita() {
    Column (
        modifier = Modifier
    ) {
        CardSuperior()
        CardInferior()
    }

}

@Preview(showBackground = true)
@Composable
fun CartaoVisitaPreview() {
    CartaoVisitaTheme {
        LayoutFinalCartaoVisita()
    }
}