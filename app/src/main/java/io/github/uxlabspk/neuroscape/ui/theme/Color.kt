package io.github.uxlabspk.neuroscape.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


// Colors
val BlueColor = Color(0xFF0080FF)
val RedColor = Color(0xFFFF1F00)
val LightGrayColor = Color(0xFFD9D9D9)
val OffWhiteColor = Color(0xFFF7F7F7)
val GrayColor = Color(0xFF4E4E4E)
val BlackColor = Color(0xFF000000)
val LightTextColor = Color(0xFF444444)
val WhiteColor = Color(0xFFFFFFFF)

val bg_Dark = LightTextColor
val bg_Light = WhiteColor

sealed class ThemeColors(
    val bgColor: Color,
    val bgCard: Color,
    val text: Color
)  {
    object Night: ThemeColors(
        bgColor = bg_Dark,
        bgCard = GrayColor,
        text = WhiteColor
    )
    object Day: ThemeColors(
        bgColor = bg_Light,
        bgCard = OffWhiteColor,
        text = LightTextColor
    )
}