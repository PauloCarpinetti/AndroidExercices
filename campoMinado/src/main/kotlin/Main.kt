import showMilhao.Pergunta

fun main(args: Array<String>) {
    /*println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")*/

    /*tabuleiro.abrirCampo(3,3)
    tabuleiro.abrirCampo(5,5)
    tabuleiro.abrirCampo(1,7)
    tabuleiro.alternarMarcacao(3,3)
    tabuleiro.alternarMarcacao(4,4)*/

    //TabuleiroConsole(tabuleiro = Tabuleiro(6, 6, 6))

    //println(message = tabuleiro.toString())
/*
    val pergunta1 = Pergunta("Qual é a pergunta?", 1)
    val pergunta2 = Pergunta("Qual é a pergunta?", 1)

    //destructuring
    val (pergunta, resposta) = pergunta1
    println(pergunta)
    println(resposta)*/

    //println( pergunta1 == pergunta2 )

    var pergunta: Pergunta = Pergunta(1, "Pergunta 1?", 1)
    pergunta.respostaErrada1 = "resposta errada1"
    pergunta.respostaErrada2 = "resposta errada2"
    pergunta.respostaErrada3 = "resposta errada3"

    println( pergunta.pergunta )
    println( pergunta.respostaCerta )
    println( pergunta.respostaErrada1 )
    println( pergunta.respostaErrada2 )
    println( pergunta.respostaErrada3 )

}