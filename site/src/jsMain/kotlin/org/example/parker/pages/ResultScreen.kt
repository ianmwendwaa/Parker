package org.example.parker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
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
import kotlinx.serialization.modules.serializersModuleOf
import org.example.parker.backend.ServerResponse
import org.example.parker.backend.getResponse
import org.example.parker.components.CardContainerStyling
import org.example.parker.components.CoffeePalette
import org.jetbrains.compose.web.css.px

@Composable
fun ResultScreen(
    response: ServerResponse
){
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = CardContainerStyling.toModifier(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize().padding(16.px)) {
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