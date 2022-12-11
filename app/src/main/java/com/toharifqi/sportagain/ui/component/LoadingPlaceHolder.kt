package com.toharifqi.sportagain.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.toharifqi.sportagain.R
import com.toharifqi.sportagain.R.raw
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun LoadingPlaceHolder(
    modifier: Modifier = Modifier
) {
    Placeholder(
        modifier = modifier,
        rawId = raw.loading_lottie,
        text = stringResource(R.string.loading)
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun LoadingIndicatorPreview() {
    SportAgainTheme {
        LoadingPlaceHolder()
    }
}
