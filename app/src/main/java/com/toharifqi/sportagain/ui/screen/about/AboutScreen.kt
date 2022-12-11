package com.toharifqi.sportagain.ui.screen.about

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.toharifqi.sportagain.R
import com.toharifqi.sportagain.ui.theme.Navy
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    context: Context,
    onBackClick: () -> Unit
) {
    AboutContent(
        modifier = modifier,
        context = context,
        onBackClick = onBackClick
    )
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier,
    context: Context,
    onBackClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (profile, nameContainer, background, description, backArrow) = createRefs()
        val uriHandler = LocalUriHandler.current

        Image(
            painter = painterResource(R.drawable.about_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(description) {
                    top.linkTo(nameContainer.bottom)
                    height = Dimension.fillToConstraints
                }
                .padding(vertical = 60.dp, horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
                ,
                onClick = {
                    uriHandler.openUri(context.getString(R.string.author_linkedin))
                }
            ) {
                Text(
                    text = stringResource(R.string.linkedin)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
                ,
                onClick = {
                    uriHandler.openUri(context.getString(R.string.author_github))
                }
            ) {
                Text(
                    text = stringResource(R.string.github)
                )
            }
        }
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(nameContainer) {
                    top.linkTo(background.bottom)
                    bottom.linkTo(background.bottom)
                },
            elevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.author_name),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(R.string.author_email),
                        style = MaterialTheme.typography.body1.copy(
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }
        }
        Image(
            painter = painterResource(R.drawable.author),
            contentDescription = "author photo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(160.dp)
                .border(4.dp, MaterialTheme.colors.primary, CircleShape)
                .clip(CircleShape)
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    bottom.linkTo(nameContainer.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Icon(
            tint = Navy,
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clickable { onBackClick() }
                .constrainAs(backArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutContentPreview() {
    SportAgainTheme {
        val context = LocalContext.current
        AboutScreen(context = context) {}
    }
}
