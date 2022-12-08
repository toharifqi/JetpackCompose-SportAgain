package com.toharifqi.sportagain.ui.screen.about

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    AboutContent(modifier)
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier,
) {
    Text(text = "About")
}
