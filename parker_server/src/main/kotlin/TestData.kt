package com.example

import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.netty.Netty
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ServerTestResponse(val response: String)
suspend fun testPostData(): ServerTestResponse? {
    val client = io.ktor.client.HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    val url = "http://127.0.0.1:8081/dump"
    val data = Data("Sample content from server tests")

    val response = client.post(url){
        contentType(ContentType.Application.Json)
        setBody(Json.encodeToString(data))
    }
    if (response.status != HttpStatusCode.OK){
        println("Could not post payload!")
        return null
    }
    return response.body<ServerTestResponse>()
}