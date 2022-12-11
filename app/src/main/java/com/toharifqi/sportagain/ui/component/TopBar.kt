package com.toharifqi.sportagain.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.toharifqi.sportagain.R
import com.toharifqi.sportagain.R.string
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(18.dp)
                .size(48.dp)
            ,
            painter = painterResource(R.drawable.main_logo),
            contentDescription = null
        )
        Text(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(string.app_name)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    SportAgainTheme {
        TopBar()
    }
}