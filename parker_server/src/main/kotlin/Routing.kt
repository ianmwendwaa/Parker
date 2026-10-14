package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.resources.*
import io.ktor.server.request.receive
import io.ktor.server.request.receiveText
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
data class Data(val content: String)

fun Application.configureRouting() {
    routing {
        post("/dump") {
            run {
                val result = testPostData()
                println("Server result: $result")
            }
        }
        get("/") {
            call.respond("Hello")
        }
        get<Articles> { article ->
            // Get all articles ...
            call.respond("List of articles sorted starting from ${article.sort}")
        }
        get("json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }

    }
}