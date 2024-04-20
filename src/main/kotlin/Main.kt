public fun doHttpCall() {
    println("calling load http client")
    val content = getKtorIo()
    println("reponse: $content")
}

public fun main() {
    doHttpCall()
}