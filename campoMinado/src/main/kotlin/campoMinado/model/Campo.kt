package campoMinado.model

import campoMinado.exception.ExplosaoException

class Campo (val linha: Int, val coluna: Int ){

    private var minado: Boolean = false
    private var aberto: Boolean = false

    private var marcado: Boolean = false
    private var vizinhos = arrayListOf<Campo>()


    fun adicionarVizinho(vizinho: Campo): Boolean {
        val linhaDiferente: Boolean = linha != vizinho.linha
        val colunaDiferente: Boolean = coluna != vizinho.coluna
        val diagonal: Boolean = linhaDiferente && colunaDiferente

        val deltaLinha: Int = Math.abs(linha - vizinho.linha)
        val deltaColuna: Int = Math.abs(coluna - vizinho.coluna)
        val deltaGeral: Int = deltaLinha + deltaColuna

        return if ( deltaGeral == 1 && !diagonal ) {
            vizinhos.add(vizinho)
            true
        } else  if ( deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho)
            true
        } else {
            false
        }
    }

    fun alternarMarcacao(){
        if( !aberto ) {
            marcado = !marcado
        }
    }


    fun abrirCampo() : Boolean {
        return if ( !aberto && !marcado ){
            aberto = true
            if ( minado ) {
                throw ExplosaoException()
            }
            if ( vizinhancaSegura() ){
                vizinhos.forEach { v -> v.abrirCampo() }
            }
            true
        } else {
            false
        }

    }



    fun vizinhancaSegura(): Boolean {
        return vizinhos.stream().noneMatch { v -> v.minado }
    }

    fun isMarcado(): Boolean {
        return marcado
    }

    fun minar() {
        minado = true
    }

    fun isMinado(): Boolean {
        return minado
    }

    // TODO validate
    fun isAberto(): Boolean {
        return aberto
    }

    // TODO validate
    fun isFechado(): Boolean {
        return !isAberto()
    }

    fun objetivoAlcancado(): Boolean {
        val desvendado: Boolean = !minado && aberto
        val protegido: Boolean = minado && marcado
        return desvendado || protegido
    }


    fun minasNaVizinhanca(): Long {
        return vizinhos.stream().filter { v -> v.minado}.count()
    }

    fun reiniciar() {
        aberto = false
        minado = false
        marcado = false
    }

    override fun toString(): String {
        return if ( marcado ) {
            "X"
        } else if ( aberto && minado ) {
            "*"
        } else if ( aberto && minasNaVizinhanca() > 0 ) {
            val minas: Long = minasNaVizinhanca()
            minas.toString()
        } else if ( aberto ) {
            " "
        } else {
            "?"
        }
    }
}