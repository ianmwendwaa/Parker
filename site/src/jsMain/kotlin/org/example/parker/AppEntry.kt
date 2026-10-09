package org.example.parker

import androidx.compose.runtime.*
import androidx.compose.web.events.SyntheticMouseEvent
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextWrap
import com.varabyte.kobweb.silk.style.selectors.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.SilkWidgetColorGroups
import org.example.parker.components.CoffeePalette
import org.example.parker.pages.ChoicePage
import org.example.parker.pages.ConfirmDateDetails
import org.example.parker.pages.NoScreen
import org.example.parker.pages.SuccessScreen
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select

enum class ScreenState{
    CHOICE, SUCCESS, CONFIRM_ORDER, BOYS_DATE
}

@Page
@Composable
fun SchedulePage(){
    var currentScreen by remember { mutableStateOf(ScreenState.CHOICE) }
    var dateTimePicked by remember { mutableStateOf("") }
    var wafflesFateDecided by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().backgroundColor(CoffeePalette.PageBg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when(currentScreen){
            ScreenState.CHOICE -> ChoicePage(
                onYesClicked = { },
                onNoClicked = { currentScreen = ScreenState.BOYS_DATE },
                onDateTimeSelectionUpdated = { selectedDateTime ->
                    dateTimePicked = selectedDateTime
                    currentScreen = ScreenState.SUCCESS
                }
            )
            ScreenState.SUCCESS -> SuccessScreen(
                selectedDateTime = dateTimePicked,
                onWafflesShouldComeChanged = { wafflesFate ->
                    wafflesFateDecided = wafflesFate
                },
                onDoneButtonClicked = {
                    currentScreen = ScreenState.CONFIRM_ORDER
                }
            )
            ScreenState.CONFIRM_ORDER -> ConfirmDateDetails(
                dateTimeOrdered = dateTimePicked,
                uncWafflesFate = wafflesFateDecided,
                onBackClicked = { currentScreen = ScreenState.CHOICE }
            )

            ScreenState.BOYS_DATE -> NoScreen(onBackClicked = { currentScreen = ScreenState.CHOICE })
        }
//        ConfirmDateDetails(
//            dateTimeOrdered = "06/10/2026 15:10",
//            uncWafflesFate = "No"
//        )

    }
}

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("html, body") { Modifier.fillMaxHeight() }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        Surface(SmoothColorStyle.toModifier().fillMaxHeight()) {
            SchedulePage()
        }
    }
}