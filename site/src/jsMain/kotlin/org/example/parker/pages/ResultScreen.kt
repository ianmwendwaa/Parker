package org.example.parker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.launch
import org.example.parker.CynoResponse
import org.example.parker.EmailSender
import org.example.parker.ServerResponse
import org.example.parker.components.CardContainerStyling
import org.example.parker.components.CoffeePalette
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.EmailInput

@Composable
fun ResultScreen(

){
    var uiState by remember { mutableStateOf<ResultUiState>(ResultUiState.Loading) }

    LaunchedEffect(Unit){
        try {
            val response = EmailSender.getResponse()

            uiState = ResultUiState.Success(response)
        }catch (e: Exception){
            uiState = ResultUiState.Error(e.message ?: "Error occurred")
        }
    }

    Box(modifier = CardContainerStyling.toModifier(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize().padding(16.px),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(val state = uiState){
                ResultUiState.Loading -> {
                    SpanText(text = "Waiting...", modifier = Modifier
                        .color(CoffeePalette.TextMain)
                        .margin(bottom = 10.px)
                        .fontSize(20.px)
                        .fontWeight(FontWeight.Bold))
                }

                is ResultUiState.Error -> {
                    SpanText(text = state.message, modifier = Modifier
                        .color(CoffeePalette.TextMain)
                        .margin(bottom = 10.px)
                        .fontSize(20.px)
                        .fontWeight(FontWeight.Bold))
                }
                is ResultUiState.Success -> {
                    val response = state.data
                    SpanText(text = response.status, modifier = Modifier
                        .color(CoffeePalette.TextMain)
                        .margin(bottom = 10.px)
                        .fontSize(20.px)
                        .fontWeight(FontWeight.Bold))
                    SpanText(text = response.data, modifier = Modifier.color(CoffeePalette.TextSub).fontSize(15.px)
                        .fontWeight(FontWeight.SemiBold))
                }
            }
        }
    }
}