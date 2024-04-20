import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
}
private val httpClient = HttpClient(CIO) {
    install(
        ContentNegotiation
    ) {
        json(json = json)
    }
}

public fun getKtorIo(): String = runBlocking {
    println("returning http client")
    httpClient.get("https://ktor.io").let {
        val content = it.bodyAsText()
        "${it.status} content: ${content.lines().size} lines"
    }
}
