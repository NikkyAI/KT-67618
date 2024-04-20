public fun doHttpCall() {
    println("calling load http client")
    val content = getKtorIo()
    println(content)
}

public fun main() {
    doHttpCall()
}