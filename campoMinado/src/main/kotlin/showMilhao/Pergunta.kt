package showMilhao

data class Pergunta(val id: Int, val pergunta: String, val respostaCerta: Int) {

    var respostaErrada1: String? = null
    var respostaErrada2: String? = null
    var respostaErrada3: String? = null
}
