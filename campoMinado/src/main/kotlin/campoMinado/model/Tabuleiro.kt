package campoMinado.model

import campoMinado.exception.ExplosaoException


class Tabuleiro (val linhas: Int, val colunas: Int, val minas: Int) {

    private val campos = arrayListOf<Campo>()

    init {
        gerarCampos()
        associarOsVizinhos()
        sortearMinas()
    }


    private fun gerarCampos() {
        for ( l in 0 until linhas) {
            for ( c in 0 until colunas) {
                campos.add(Campo(l, c))
            }
        }
    }

    private fun associarOsVizinhos() {
        for ( campo1 in campos ) {
            for ( campo2 in campos ) {
                campo1.adicionarVizinho(campo2)
            }
        }
    }


    private fun sortearMinas() {
        var minasArmadas: Long = 0
        do {
            minasArmadas = campos.stream().filter { c -> c.isMinado() }.count()
            var aleatorio: Int =  ((Math.random() * campos.size).toInt())
            campos[aleatorio].minar()

        } while ( minasArmadas < minas)
    }

    fun abrirCampo(linha: Int, coluna: Int) {
       try {
           campos.stream().filter { c -> c.linha == linha && c.coluna == coluna }
               .findFirst()
               .ifPresent { c -> c.abrirCampo()}
       } catch (e: ExplosaoException) {
           campos.forEach() { c -> c.abrirCampo()}
           throw e
       }
    }

    fun alternarMarcacao(linha: Int, coluna: Int) {
        campos.stream().filter { c -> c.linha == linha && c.coluna == coluna }
                .findFirst()
                .ifPresent { c -> c.alternarMarcacao()}
    }

    fun objetivoAlcancado(): Boolean {
        return campos.stream().allMatch { c -> c.objetivoAlcancado() }
    }

    fun reiniciarJogo() {
        campos.stream().forEach { c -> c.reiniciar() }
        sortearMinas()
    }

    override fun toString(): String {
        val sb: StringBuilder = java.lang.StringBuilder()
        var i = 0

        for ( l in 0 until linhas) {
            for ( c in 0 until colunas) {
                sb.append(" ")
                sb.append(campos[i])
                sb.append(" ")
                i++
            }
            sb.append("\n")
        }

        return "$sb"
    }

}


