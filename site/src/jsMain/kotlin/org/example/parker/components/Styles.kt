package org.example.parker.components

import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.focus
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

object CoffeePalette {
    val PageBg = Color("#FDFBF7")       // Cream / Warm Milk Foam Base
    val CardBg = Color("#F4EDE4")       // Latte / Soft Warm Linen Accent
    val TextMain = Color("#2B1B17")     // Dark Roasted Espresso / Near-Black
    val TextSub = Color("#5C4033")      // Medium Roast Mocha / Dark Brown
    val PrimaryBtn = Color("#C49A6C")   // Golden Caramel Accent
    val SecondaryBtn = Color("#8B5A2B") // Deep Cinnamon Wood
    val BorderColor = Color("#D2B48C")  // Warm Sand / Light Tan
    val NoHoverBtn = Color("#ff0051")
}

val CardContainerStyling = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .height(400.px)
            .minWidth(350.px)
            .padding(16.px)
            .margin(50.px)
            .backgroundColor(CoffeePalette.CardBg)
            .borderRadius(15.px)
    }
}

val YesButtonStyling = CssStyle {
    base {
        Modifier.padding(10.px).margin(right = 10.px).height(40.px).width(100.px)
            .backgroundColor(CoffeePalette.PrimaryBtn)
    }
    hover {
        Modifier.backgroundColor(CoffeePalette.TextSub)
    }
}

val NoButtonStyling = CssStyle {
    var width = 100
    var height = 40
    base {
        Modifier.padding(10.px).height(height.px).width(width.px)
            .backgroundColor(Color.transparent).border(2.px, LineStyle.Solid, CoffeePalette.SecondaryBtn)

    }
    hover {
        var newHeight = height - 2
        var newWith = width - 2
        Modifier.backgroundColor(CoffeePalette.NoHoverBtn).height(newHeight.px).width(newWith.px)
    }
}

val DateTimeInputStyling = CssStyle{
    base {
        Modifier.width(250.px).height(50.px)
            .padding(left = 16.px, right = 10.px)
            .color(Color("#928f8f"))
            .fontSize(15.px)
            .borderRadius(10.px)
            .border(width = 2.px, style = LineStyle.Solid, color = Color.gray)
            .cursor(Cursor.Pointer)
            .margin(bottom = 10.px)
    }
    focus {
        Modifier
            .boxShadow(0.px, 0.px, 0.px, 3.px, CoffeePalette.BorderColor)
    }
}