public fun doHttpCall() {
    println("calling getKtorIo")
    val content = getKtorIo()
    println("response: $content")
}

public fun main() {
    doHttpCall()
}