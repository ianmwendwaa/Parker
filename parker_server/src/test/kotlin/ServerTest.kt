package com.example

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.testing.testApplication
import io.ktor.util.reflect.TypeInfo
import kotlin.test.*

class ServerTest {

    @Test
    fun `test root endpoint`() = testApplication {
        // loads default configuration
        configure()
        // verify server root returns 200
        assertEquals(HttpStatusCode.OK, client.get("/").status)
    }

    @Test
    fun testPostData() = testApplication {
        val targetURL = "http://"
        val payloadSrc = Data("Test data from server tests")
    }
    @Test
    fun testConfig() = testApplication{
        configure()
        routing {
            get("/message"){
                call.respond("Testing server...")
            }
            post("/dump"){
                val payloadDump = call.receive<Data>()
                call.respond("Received payload with content: ${payloadDump.content}")
            }
        }
    }

}
