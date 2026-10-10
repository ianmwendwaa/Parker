package org.example.parker.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import kotlinx.coroutines.launch
import org.example.parker.CynoResponse
import org.example.parker.EmailSender
import org.example.parker.ServerResponse
import org.example.parker.components.CoffeePalette

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

    Column(
        modifier = Modifier.fillMaxSize().backgroundColor(CoffeePalette.PageBg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when(currentScreen){
            ScreenState.CHOICE -> ChoicePage(
                onNoClicked = { currentScreen = ScreenState.BOYS_DATE },
                onDateTimeSelectionUpdated = { selectedDateTime ->
                    dateTimePicked = selectedDateTime
                    currentScreen = ScreenState.SUCCESS
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
                        EmailSender.sendDateEmail(dateTimePicked)
                    }},
                onBackClicked = { currentScreen = ScreenState.CHOICE }
            )

            ScreenState.BOYS_DATE -> NoScreen(
                onBackClicked = { currentScreen = ScreenState.CHOICE },
                onConfirmRejectionClicked = { coroutineScope.launch { EmailSender.sendDateEmail("Nope") }}
            )

            ScreenState.RESULT -> ResultScreen(

            )
        }
    }
}

