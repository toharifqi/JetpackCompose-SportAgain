package com.toharifqi.sportagain.ui.component

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.toharifqi.sportagain.R.raw
import com.toharifqi.sportagain.R.string
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(RawRes(raw.loading_lottie))

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = modifier.size(120.dp),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = modifier.padding(top = 220.dp),
            text = stringResource(string.loading),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            style = MaterialTheme.typography.body1.copy(
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun LoadingIndicatorPreview() {
    SportAgainTheme {
        LoadingIndicator()
    }
}
