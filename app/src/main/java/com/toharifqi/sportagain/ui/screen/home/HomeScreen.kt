package com.toharifqi.sportagain.ui.screen.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeContent(modifier)
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
) {
    Text(text = "Home")
}
