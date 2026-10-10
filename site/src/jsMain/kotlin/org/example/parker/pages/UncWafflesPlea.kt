package org.example.parker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.web.events.SyntheticMouseEvent
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextWrap
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textWrap
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.example.parker.components.CardContainerStyling
import org.example.parker.components.CoffeePalette
import org.example.parker.components.YesButtonStyling
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.outline
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select

@Composable
fun UncWafflesPlea(
    selectedDateTime: String,
    onWafflesShouldComeChanged: (String) -> Unit,
    onDoneButtonClicked: (SyntheticMouseEvent) -> Unit
){
    var shouldWafflesComes by remember { mutableStateOf("") }
    Box(modifier = CardContainerStyling.toModifier().height(650.px)) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText(text = "Yay! See you on $selectedDateTime", modifier = Modifier
                .color(CoffeePalette.TextMain)
                .fontSize(28.px)
                .margin(top = 15.px)
                .textAlign(TextAlign.Center))
            Image(
                src = "/unc_waffles_broken.jpg",
                description = "Missing Hipster",
                modifier = Modifier.width(200.px).height(200.px)
                    .borderRadius(7.px)
                    .objectFit(ObjectFit.Cover)
                    .border(width = 1.px, style = LineStyle.Solid, color = CoffeePalette.BorderColor)
            )
            SpanText(text = "One more thing: Uncle Waffles wants to come along\n. He says you two have 'issues' to sort " +
                    "out. Do you want him to come along?",
                modifier = Modifier
                .fontSize(20.px).textWrap(TextWrap.Wrap)
                .padding(15.px)
                .margin(left = 20.px, right = 20.px).color(CoffeePalette.TextSub))

            Select(attrs = {
                onChange { event ->
                    shouldWafflesComes = event.value ?: "Yes"
                    onWafflesShouldComeChanged(shouldWafflesComes)
                }
                style {
                    width(120.px)
                    height(40.px)
                    padding(10.px)
                    fontSize(15.px)
                    backgroundColor(Colors.White)
                    borderRadius(6.px)
                    margin(10.px)
                    border(1.px, LineStyle.Solid, Color("#E5E5E5"))
                    outline("none")
                }
            }) {
                Option(value = "Yes"){ SpanText(text = "Yes", modifier = Modifier.fontSize(13.px))}
                Option(value = "No"){ SpanText(text = "No", modifier = Modifier.fontSize(13.px))}
            }
            Button(
                modifier = YesButtonStyling.toModifier(),
                onClick = onDoneButtonClicked
            ){
                SpanText(text = "Done", modifier = Modifier.color(Color.white))
            }
        }
    }
}
