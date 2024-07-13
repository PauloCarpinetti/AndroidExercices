package campoMinado.visao

import campoMinado.exception.ExplosaoException
import campoMinado.exception.SairException
import campoMinado.model.Tabuleiro
import java.lang.System.`in`
import java.util.*

class TabuleiroConsole(val tabuleiro: Tabuleiro) {

    private var entrada: Scanner = Scanner(`in`)

    init {
        executarJogo()
    }

    private fun executarJogo() {
        try {
            var continuar: Boolean = true

            while (continuar) {

                cicloDoJogo()

                System.out.println("Outra partida? (S/n) ")
                val resposta: String = entrada.nextLine()

                if ( resposta.equals("n") ) {
                    continuar = false
                } else {
                    tabuleiro.reiniciarJogo()
                }
            }
        } catch (e: SairException) {
            System.out.println("Tchau!!!")
        } finally {
            entrada.close()
        }
    }

    private fun cicloDoJogo() {
        try {
            while ( !tabuleiro.objetivoAlcancado() ) {
                println(tabuleiro)
                var digitado: String = capturarValorDigitado("Digite (x, y): ")
                val xy: Iterator<Int> =
                    Arrays.stream(digitado.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray())
                        .map { e -> e.trim().toInt() }.iterator()
                digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ")
                if ("1" == digitado) {
                    tabuleiro.abrirCampo(xy.next(), xy.next())
                } else if ("2" == digitado) {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next())
                }
            }

            println(tabuleiro)
            println("Voçe venceu!!")
        } catch (e: ExplosaoException) {
            println(tabuleiro)
            println("Voçe perdeu!")
        }
    }

    private fun capturarValorDigitado(texto: String): String {
        println(texto)
        val digitado: String = entrada.nextLine()

        if ("sair".contentEquals(digitado)) {
            throw SairException()
        }
        return digitado
    }
}

