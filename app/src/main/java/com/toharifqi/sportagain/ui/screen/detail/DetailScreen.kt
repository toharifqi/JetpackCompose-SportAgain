package com.toharifqi.sportagain.ui.screen.detail

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.toharifqi.sportagain.R
import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.di.Injection
import com.toharifqi.sportagain.ui.ViewModelFactory
import com.toharifqi.sportagain.ui.component.SportFavoriteItem
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun DetailScreen(
    sport: SportDomainData,
    context: Context,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideSportRepository(context))
    ),
    navigateBack: () -> Unit
) {
    viewModel.setSportId(sport.id)
    viewModel.favoriteStatus.collectAsState(initial = false).value.let {
        DetailContent(
            sport = sport,
            favoriteStatus = it,
            onFavoriteClick = {
                viewModel.changeFavorite(sport)
            },
            onBackClick = navigateBack
        )
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    sport: SportDomainData,
    favoriteStatus: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (thumbnail, sportItem, description, backArrow) = createRefs()

        AsyncImage(
            model = sport.thumbnail,
            contentDescription = "sport thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .constrainAs(thumbnail) {
                    top.linkTo(parent.top)
                }
        )
        Column(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(thumbnail.bottom)
                    height = Dimension.fillToConstraints
                }
                .verticalScroll(rememberScrollState())
                .padding(top = 80.dp, start = 18.dp, end = 18.dp)
        ) {
            Text(
                text = sport.description,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(220.dp))
        }
        SportFavoriteItem(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .constrainAs(sportItem) {
                    top.linkTo(thumbnail.bottom)
                    bottom.linkTo(thumbnail.bottom)
                },
            sport = sport,
            isFavorite = favoriteStatus,
            onFavoriteClick = onFavoriteClick,
            onClick = {}
        )
        Icon(
            tint = MaterialTheme.colors.primary,
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
fun DetailContentPreview() {
    val dummySport = SportDomainData(
        "102",
        "Soccer",
        "TeamvsTeam",
        "https://www.thesportsdb.com/images/sports/soccer.jpg",
        "https://www.thesportsdb.com/images/icons/sports/soccer.png",
        "Association football, more commonly known as football or soccer, bla bla bla as you wish."
    )
    SportAgainTheme {
        DetailContent(
            sport = dummySport,
            favoriteStatus = true,
            onFavoriteClick = {},
            onBackClick = {}
        )
    }
}
