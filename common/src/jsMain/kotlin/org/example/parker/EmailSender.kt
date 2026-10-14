package org.example.parker

import com.varabyte.kobweb.browser.http.bodyOf
import com.varabyte.kobweb.browser.http.http
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

const val url = "http://127.0.0.1:5000/api/parker-data/"
@Serializable
data class EmailData(val selectedDateTime: String, val wfate: String)

object EmailSender{
    private var responseBytes: Response = Response()
    suspend fun sendDateEmail(dateTimeOrdered: String, wafflesFate: String) {
        val parkerData = EmailData(selectedDateTime = dateTimeOrdered, wfate = wafflesFate)
        val jsonPayload = Json.encodeToString(parkerData)
        responseBytes = window.http.post(
            resource = url,
            body = bodyOf(jsonPayload.encodeToByteArray()),
            headers = mapOf("Content-Type" to "application/json",
                "ngrok-skip-browser-warning" to "true",
                "Access-Control-Allow-Origin" to "true")
        )
    }
    suspend fun getResponse(): ServerResponse{
        val responseString = responseBytes.text().await()

        if (responseString.isEmpty()){
            return ServerResponse(status = "Waffles may be hacking", data = "Server returned empty JSON response.")
        }
        return Json.decodeFromString<ServerResponse>(responseString)
    }
}