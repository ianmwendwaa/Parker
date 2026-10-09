package org.example.parker.pages

import androidx.compose.runtime.*
import androidx.compose.web.events.SyntheticMouseEvent
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.dom.observers.invoke
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorPalette
import org.example.parker.components.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px


@Composable
fun ChoicePage(
    onYesClicked: (SyntheticMouseEvent) -> Unit,
    onNoClicked: (SyntheticMouseEvent) -> Unit,
    onDateTimeSelectionUpdated: (String) -> Unit,
){
    var toggleDateTimeVisibility by remember { mutableStateOf(false) }
    var dateTimeSelected by remember { mutableStateOf("") }
    Box(modifier = CardContainerStyling.toModifier().height(600.px), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.px),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Welcoming text
            SpanText(
                text = "5 HOURS WITH FREDDY: CINGULOMANIA EDITION", modifier = Modifier
                    .fontSize(24.px)
                    .fontWeight(FontWeight.Bold)
                    .color(CoffeePalette.TextMain)
                    .textAlign(TextAlign.Center)
                    .letterSpacing(2.px)
            )

            // Subtext
            SpanText(
                text = "Tired of picturing a date, might as well photograph it.\n Picture-perfect this time.", modifier = Modifier
                    .fontSize(20.px)
                    .fontWeight(FontWeight.SemiBold)
                    .color(CoffeePalette.TextSub)
                    .margin(top = 10.px, bottom = 16.px)
                    .textAlign(TextAlign.Center)
                    .padding(16.px)
            )

            // Unsuspecting brother
            Image(
                src = "/mwendwa_luvs_u.jpg",
                description = "Unsuspecting brother",
                modifier = Modifier.width(200.px).height(200.px)
                    .borderRadius(7.px).objectFit(ObjectFit.Cover).margin(bottom = 10.px)
                    .border(width = 1.px, style = LineStyle.Solid, color = CoffeePalette.BorderColor),
            )

            // Date time picker. Initially, set it to hidden
            if (toggleDateTimeVisibility) {
                SpanText(text = "I'm honestly okay with Friday 9th, but if you aren't, choose your preferred date.",
                    modifier = Modifier.margin(bottom = 5.px)
                        .fontSize(18.px)
                        .color(CoffeePalette.TextSub)
                        .textAlign(TextAlign.Center)
                        .padding(left = 16.px, right = 16.px))
                Input(
                    type = InputType.DateTimeLocal,
                    value = dateTimeSelected,
                    onValueChange = { newDateTime ->
                        dateTimeSelected = newDateTime
                    },
                    placeholder = "Select the when",
                    modifier = DateTimeInputStyling.toModifier()
                )
            }
            // Buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                com.varabyte.kobweb.silk.components.forms.Button(
                    onClick = {
                        if (!toggleDateTimeVisibility) {
                            // Toggle the date/time input field and change the btn text
                            toggleDateTimeVisibility = true
                        } else {
                            if (dateTimeSelected.isNotEmpty()) {
                                onDateTimeSelectionUpdated(dateTimeSelected)
                            }
                        }
                    },
                    modifier = YesButtonStyling.toModifier()
                ) {
                    SpanText(if (!toggleDateTimeVisibility) "Yes" else "Done", modifier = Modifier.color(CoffeePalette.CardBg))
                }
                // Pop the no button off the screen
                if (!toggleDateTimeVisibility) {
                    com.varabyte.kobweb.silk.components.forms.Button(
                        onClick = onNoClicked,
                        modifier = NoButtonStyling.toModifier()
                    ) {
                        SpanText("No", modifier = Modifier.color(CoffeePalette.TextSub))
                    }
                }
            }
        }
    }
}