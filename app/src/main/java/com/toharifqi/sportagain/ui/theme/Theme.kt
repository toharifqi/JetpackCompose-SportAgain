package com.toharifqi.sportagain.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Navy,
    primaryVariant = Teal200,
    secondary = Green,
    onPrimary = Chartreuse
)

private val LightColorPalette = lightColors(
    primary = LightBlue,
    primaryVariant = Teal200,
    secondary = Green,
    onPrimary = Navy,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SportAgainTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors: Colors

    if (darkTheme) {
        colors = DarkColorPalette
        systemUiController.setSystemBarsColor(
            color = Navy
        )
    } else {
        colors = LightColorPalette
        systemUiController.setSystemBarsColor(
            color = LightBlue
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}