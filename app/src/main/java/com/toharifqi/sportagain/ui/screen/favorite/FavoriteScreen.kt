package com.toharifqi.sportagain.ui.screen.favorite

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
) {
    FavoriteContent(modifier)
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
) {
    Text(text = "Favorite")
}