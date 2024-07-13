package model

import campoMinado.exception.ExplosaoException
import campoMinado.model.Campo
import org.junit.Assert
import org.junit.Test
//import org.junit.jupiter.api.Test

class CampoUnitTest {

    private var campo33 = Campo(3,3)

    // tres na linha de cima
    private var campo22 = Campo(2,2)
    private var campo23 = Campo(2,3)
    private var campo24 = Campo(2,4)

    private var vizinhoDoVizinho1 = Campo(1,1)

    private var explosao = ExplosaoException()



    @Test
    fun testeVizinhoDistancia1Esquerda(){
        var vizinho = Campo(3,2)
        var resultado: Boolean = campo33.adicionarVizinho(vizinho)
        assert(resultado)
    }

    @Test
    fun testeVizinhoDistancia1Direita(){
        var vizinho = Campo(3,4)
        var resultado: Boolean = campo33.adicionarVizinho(vizinho)
        assert(resultado)
    }

    @Test
    fun testeVizinhoDistancia1Emcima(){
        var vizinho = Campo(2,3)
        var resultado: Boolean = campo33.adicionarVizinho(vizinho)
        assert(resultado)
    }

    @Test
    fun testeVizinhoDistancia1Embaixo(){
        var vizinho = Campo(4,3)
        var resultado: Boolean = campo33.adicionarVizinho(vizinho)
        assert(resultado)
    }

    @Test
    fun testeVizinhoDistancia2(){
        var vizinho = Campo(2,2)
        var resultado: Boolean = campo33.adicionarVizinho(vizinho)

        assert(resultado)
    }

    @Test
    fun testeNaoVizinho(){
        var vizinho = Campo(4,5)
        var resultado: Boolean = campo33.adicionarVizinho(vizinho)

        assert( !resultado )
    }

    @Test
    fun testeValorPadraoMarcacao(){
        assert( !campo33.isMarcado() )
    }

    @Test
    fun testeAlternarMarcacao(){
        campo33.alternarMarcacao()
        assert( campo33.isMarcado() )
    }

    @Test
    fun testeAlternarMarcacaoDuasVezes(){
        campo33.alternarMarcacao()
        campo33.alternarMarcacao()
        assert( !campo33.isMarcado() )
    }

    @Test
    fun testeAbrirNaoMarcadoNaoMinado(){
        assert( campo33.abrirCampo() )
    }

    @Test
    fun testeAbrirNaoMinadoMarcado(){
        campo33.alternarMarcacao()
        assert( !campo33.abrirCampo() )
    }

    // Exception Tests
    @Test
    fun testeAbrirMinadoNaoMarcado(){
        campo33.minar()
        Assert.assertThrows(ExplosaoException::class.java) { campo33.abrirCampo() }
    }

    @Test
    fun testeAbrirMinadoMarcado(){
        campo33.alternarMarcacao()
        campo33.minar()
        assert( !campo33.abrirCampo() )
    }

    @Test
    fun testeAbrirComVizinhos1() {
        campo33.adicionarVizinho(campo22)

        campo22.adicionarVizinho(vizinhoDoVizinho1)

        campo33.abrirCampo()

        assert(campo22.isAberto() && vizinhoDoVizinho1.isAberto())
    }

    @Test
    fun testeAbrirComVizinhos2() {
        campo33.adicionarVizinho(campo22)

        campo22.adicionarVizinho(vizinhoDoVizinho1)

        vizinhoDoVizinho1.minar()
        campo33.abrirCampo()

        assert(campo22.isAberto() && vizinhoDoVizinho1.isFechado())
    }
}
