package com.toharifqi.sportagain.ui.screen.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
) {
    DetailContent(modifier)
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
) {
    Text(text = "Detail")
}