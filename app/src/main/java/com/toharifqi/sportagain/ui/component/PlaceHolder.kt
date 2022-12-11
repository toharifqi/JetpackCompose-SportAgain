package com.toharifqi.sportagain.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun Placeholder(
    modifier: Modifier = Modifier,
    rawId: Int,
    text: String
) {
    val composition by rememberLottieComposition(RawRes(rawId))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(160.dp),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier.padding(top = 18.dp),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            style = MaterialTheme.typography.body1.copy(
                fontStyle = FontStyle.Italic
            )
        )
    }
}
