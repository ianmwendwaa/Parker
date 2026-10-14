package org.example.parker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextAlign
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
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.silk.components.graphics.Image
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

data class Result(val title: String, val description: String)
@Composable
fun ResultScreen(
    dateAccepted: Boolean,
    dateChoice: String
){
//    var uiState by remember { mutableStateOf<ResultUiState>(ResultUiState.Loading) }

//    LaunchedEffect(Unit){
//        try {
//            val response = EmailSender.getResponse()
//
//            uiState = ResultUiState.Success(response)
//        }catch (e: Exception){
//            uiState = ResultUiState.Error(e.message ?: "Error occurred")
//        }
//    }

    val formResult = if (dateAccepted)
        Result("Confirmed! See you on $dateChoice",
            "Dress up for a PARK DATE Thiccster; grass is greener on this side.")
    else
        Result("Guess narudi site.", "Doesn't change the fact that I still lowkey miss you " +
                "Hipster Freddy.")
    Box(modifier = CardContainerStyling.toModifier(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize().padding(16.px),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                src = if (dateAccepted) "/confirmed_mod.png" else "/confirmed_no_mod.png",
                description = "mwendwas_heart_aftermath",
                modifier = Modifier.size(200.px).objectFit(ObjectFit.Cover)
            )
            SpanText(text = formResult.title, modifier = Modifier
                .color(CoffeePalette.TextMain)
                .margin(bottom = 14.px)
                .fontSize(23.px)
                .fontWeight(FontWeight.Bold)
                .textAlign(TextAlign.Center))

            SpanText(text = formResult.description, modifier = Modifier
                .color(CoffeePalette.TextSub)
                .margin(bottom = 10.px)
                .fontSize(17.px)
                .textAlign(TextAlign.Center)
                .fontWeight(FontWeight.SemiBold))
        }
    }
}