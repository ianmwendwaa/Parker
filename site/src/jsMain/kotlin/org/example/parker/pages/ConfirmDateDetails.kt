package org.example.parker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.web.events.SyntheticMouseEvent
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier
import org.example.parker.components.CoffeePalette
import org.example.parker.components.NoButtonStyling
import org.example.parker.components.YesButtonStyling
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

data class AttendeeDetails(val number: String, val name: String, val count: String, val comment: String)

val TableHeaderStyling = CssStyle {
    base {
        Modifier.fontSize(18.px).margin(right = 25.px).fontWeight(FontWeight.Bold)
    }
}

data class UncWafflesCommentary(val count: String, val comment: String)
@Composable
fun ConfirmDateDetails(
    dateTimeOrdered: String,
    onBackClicked: (SyntheticMouseEvent) -> Unit,
    onConfirmDateClicked: (SyntheticMouseEvent) -> Unit,
    uncWafflesFate: String
) {
    println(uncWafflesFate)
    val uncs = if (uncWafflesFate == "No")
        UncWafflesCommentary("_", "Just me + u in the picture.")
    else UncWafflesCommentary("1", "Three's Company.")

    val attendanceList = listOf(
        AttendeeDetails("1", "Thiccy Freddy", "1", "(just what I ordered btw)"),
        AttendeeDetails("2", "Thiccy Mwendwa", "1", "Poly C; decent security detail."),
        AttendeeDetails("3", "Uncle Waffles", uncs.count, uncs.comment),
        AttendeeDetails("4", "Rob the Builder", "_", "In prison")
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minWidth(350.px)
            .backgroundColor(CoffeePalette.CardBg)
            .borderRadius(12.px)
            .padding(32.px),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // --- TOP HEADER AREA ---
            Column(
                modifier = Modifier.fillMaxWidth().margin(bottom = 40.px),
                verticalArrangement = Arrangement.spacedBy(5.px ),
                horizontalAlignment = Alignment.Start
            ) {
                // Left Title text
                SpanText(
                    text = "Thiccsters' Date Confirmation",
                    modifier = Modifier.fontSize(20.px).fontWeight(FontWeight.Bold).color(CoffeePalette.TextMain)
                )

                // Right effective date string
                Column(verticalArrangement = Arrangement.spacedBy(3.px)) {
                    SpanText(
                        text = "Effective on: ",
                        modifier = Modifier.fontSize(17.px)
                            .fontWeight(FontWeight.Bold)
                            .color(CoffeePalette.TextMain)
                    )
                    SpanText(
                        text = dateTimeOrdered,
                        modifier = Modifier.fontSize(16.px).color(CoffeePalette.TextMain)
                    )
                }
            }

            // Order summary
            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .overflow(Overflow.Auto) // Crucial: Allows side-swiping gestures on small smartphone viewports
                    .toAttrs()
            ) {
                Table(attrs = {
                    style {
                        width(100.percent)
                        borderCollapse(BorderCollapse.Collapse)
                        minWidth(600.px)
                    }
                }) {
                    // Header Layout Column Columns
                    Thead {
                        Tr {
                            // Empty column header above IDs (1, 2, 3)
                            Th(attrs = { style { width(40.px); padding(12.px); textAlign("left") } }) {}

                            Th(attrs = { style { padding(12.px); textAlign("left"); fontSize(16.px); fontWeight("bold"); color(Color.black) } }) {
                                Text("Attendee")
                            }
                            Th(attrs = { style { padding(12.px); textAlign("center"); fontSize(16.px); fontWeight("bold"); color(
                                Color.black) } }) {
                                Text("Count")
                            }
                            Th(attrs = { style { padding(12.px); textAlign("center"); fontSize(16.px); fontWeight("bold"); color(Color.black) } }) {
                                Text("Comments")
                            }
                        }
                    }

                    // Body List
                    Tbody {
                        attendanceList.forEach { attendee ->
                            Tr {
                                Td(attrs = { style { padding(12.px); fontSize(16.px); color(Color.black); textAlign("left") } }) {
                                    Text("${attendee.number}.")
                                }

                                // Attendee Name column
                                Td(attrs = { style { padding(12.px); fontSize(16.px); color(Color.black); textAlign("left") } }) {
                                    Text(attendee.name)
                                }

                                // Count column (Dead center alignment)
                                Td(attrs = { style { padding(12.px); fontSize(16.px); color(Color.black); textAlign("center") } }) {
                                    Text(attendee.count)
                                }

                                // Comments column (Dead center alignment)
                                Td(attrs = { style { padding(12.px); fontSize(16.px); color(Color.black); textAlign("center") } }) {
                                    Text(attendee.comment)
                                }
                            }
                        }
                    }
                }
            }

            Row(modifier = Modifier.align(Alignment.End), horizontalArrangement = Arrangement.spacedBy(7.px)) {
                com.varabyte.kobweb.silk.components.forms.Button(
                    onClick = onBackClicked,
                    modifier = NoButtonStyling.toModifier()
                        .margin(right = 50.px, top = 50.px)
                ) {
                    SpanText("Go back", modifier = Modifier.color(CoffeePalette.SecondaryBtn))
                }

                com.varabyte.kobweb.silk.components.forms.Button(
                    onClick = onConfirmDateClicked,
                    modifier = YesButtonStyling.toModifier()
                        .margin(right = 25.px, top = 50.px)
                ) {
                    SpanText("Confirm", modifier = Modifier.color(Color.white))
                }
            }
        }
    }
}