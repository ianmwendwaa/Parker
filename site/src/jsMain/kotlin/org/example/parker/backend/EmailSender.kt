package org.example.parker.backend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.browser.http.bodyOf
import com.varabyte.kobweb.browser.http.http
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.await
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response
import kotlinx.coroutines.await
const val url = "http://127.0.0.1:5000/api/parker-data/"
@Serializable
data class EmailData(val selectedDateTime: String)

suspend fun sendDateEmail(dateTimeOrdered: String) {

    val parkerData = EmailData(selectedDateTime = dateTimeOrdered)
    val json = Json.encodeToString(parkerData)
    try {
        window.http.post(
            resource = url,
            body = bodyOf(json.encodeToByteArray()),
            headers = mapOf("Content-Type" to "application/json")
        )
    }catch (e: Exception){
        println("Error: ${e.message}")
    }
}

@Serializable
data class ServerResponse(val status: String, val data: String)
suspend fun getResponse(): ServerResponse {
    var serverResponse: ServerResponse
    try {
        val response = window.fetch(url).await()

        if (response.ok) {
            val jsonString = response.text().await()
            serverResponse = Json.decodeFromString<ServerResponse>(jsonString)
        }
    }catch (e: Exception){
        print("Error: ${e.message}")
    }
    return serverResponse
}


