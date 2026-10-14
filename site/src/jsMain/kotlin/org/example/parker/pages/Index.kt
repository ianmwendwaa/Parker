package org.example.parker.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import kotlinx.coroutines.launch
import org.example.parker.CynoResponse
import org.example.parker.EmailSender
import org.example.parker.ServerResponse
import org.example.parker.components.CoffeePalette
import org.jetbrains.compose.web.css.px

enum class ScreenState{
    CHOICE, SUCCESS, CONFIRM_ORDER, BOYS_DATE, RESULT
}

@Page
@Composable
fun SchedulePage(){
    val coroutineScope = rememberCoroutineScope()
    var currentScreen by remember { mutableStateOf(ScreenState.CHOICE) }
    var dateTimePicked by remember { mutableStateOf("") }
    var wafflesFateDecided by remember { mutableStateOf("") }
    var serverResponse by remember { mutableStateOf(ServerResponse(status = "", data = "")) }
    var dateAccepted by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize().margin(10.px),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when(currentScreen){
            ScreenState.CHOICE -> ChoicePage(
                onNoClicked = { currentScreen = ScreenState.BOYS_DATE },
                onDateTimeSelectionUpdated = { selectedDateTime ->
                    dateTimePicked = selectedDateTime
                    currentScreen = ScreenState.SUCCESS
                    dateAccepted = true
                }
            )
            ScreenState.SUCCESS -> UncWafflesPlea(
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
                onConfirmDateClicked = {
                    currentScreen = ScreenState.RESULT
                    coroutineScope.launch {
                        EmailSender.sendDateEmail(dateTimePicked, wafflesFateDecided)
                    }},
                onBackClicked = { currentScreen = ScreenState.CHOICE }
            )

            ScreenState.BOYS_DATE -> NoScreen(
                onBackClicked = { currentScreen = ScreenState.CHOICE },
                onConfirmRejectionClicked = {
                    coroutineScope.launch {
                        EmailSender.sendDateEmail("Nope", "none")
                        dateAccepted = false
                    }
                    currentScreen = ScreenState.RESULT
                }
            )

            ScreenState.RESULT -> ResultScreen(
                dateAccepted,
                dateTimePicked
            )
        }
    }
}

