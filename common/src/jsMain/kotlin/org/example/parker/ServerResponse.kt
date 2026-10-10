package org.example.parker

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ServerResponse(val status: String, val data: String)

object CynoResponse{
    private val json = Json {
        ignoreUnknownKeys
    }

    suspend fun getServerResponse(): ServerResponse{
        val response = window.fetch(url).await()
        if (!response.ok) throw Exception("Network error ${response.statusText}")
        val jsonOutput = response.text().await()
        return json.decodeFromString(jsonOutput)
    }
}
