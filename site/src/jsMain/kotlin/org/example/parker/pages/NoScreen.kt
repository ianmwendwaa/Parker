package org.example.parker.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.web.events.SyntheticMouseEvent
import com.varabyte.kobweb.compose.css.BorderCollapse
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.borderCollapse
import com.varabyte.kobweb.compose.css.color
import com.varabyte.kobweb.compose.css.width
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
import kotlinx.coroutines.launch
import org.example.parker.backend.sendDateEmail
import org.example.parker.components.CoffeePalette
import org.example.parker.components.NoButtonStyling
import org.example.parker.components.YesButtonStyling
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.dpi
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Table
import org.jetbrains.compose.web.dom.Tbody
import org.jetbrains.compose.web.dom.Td
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Th
import org.jetbrains.compose.web.dom.Thead
import org.jetbrains.compose.web.dom.Tr


@Composable
fun NoScreen(
    onBackClicked: (SyntheticMouseEvent) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val attendanceList = listOf(
        AttendeeDetails("1", "Thiccy Freddy", "_", "I miss you so bad. Rethink?"),
        AttendeeDetails("2", "Thiccy Mwendwa", "1", "Just me,"),
        AttendeeDetails("3", "Uncle Waffles", "1", "Wafula,"),
        AttendeeDetails("4", "Rob the Builder", "1", "And Rob, kwa bar (I'm bailing him out)")
    )
    // Core structural card wrapper box matching your light grey theme
    Box(
        modifier = Modifier
            .width(750.px) // Plentiful breathing space for layout items
            .minHeight(420.px)
            .backgroundColor(CoffeePalette.CardBg)
            .borderRadius(12.px)
            .padding(32.px),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // --- TOP HEADER AREA ---
            Row(
                modifier = Modifier.fillMaxWidth().margin(bottom = 40.px),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Left Title text
                SpanText(
                    text = "Thiccsters' Date Decline",
                    modifier = Modifier.fontSize(20.px).fontWeight(FontWeight.Bold).color(CoffeePalette.TextMain)
                )

                // Right effective date string
                Row(verticalAlignment = Alignment.CenterVertically) {

                    SpanText(
                        text = "Guess I'm going out with the boys instead",
                        modifier = Modifier.fontSize(16.px).color(Color.black).margin(left = 6.px)
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
                        minWidth(600.px) // Enforces rigid alignment lines so table doesn't cramp up on mobile
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

                    // Body List Auto-Population Rows
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
                    modifier = YesButtonStyling.toModifier()
                        .margin(right = 50.px, top = 50.px)
                ) {
                    SpanText("Rethink", modifier = Modifier.color(Color.white))
                }

                com.varabyte.kobweb.silk.components.forms.Button(
                    onClick = {
                        coroutineScope.launch {
                            sendDateEmail("Nope")
                        }
                    },
                    modifier = NoButtonStyling.toModifier()
                        .margin(right = 25.px, top = 50.px)
                ) {
                    SpanText("Confirm", modifier = Modifier.color(CoffeePalette.SecondaryBtn))
                }
            }
        }
    }
}