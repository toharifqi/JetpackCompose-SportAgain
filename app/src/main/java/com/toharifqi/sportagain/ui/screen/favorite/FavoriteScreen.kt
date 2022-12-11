package com.toharifqi.sportagain.ui.screen.favorite

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.di.Injection
import com.toharifqi.sportagain.ui.ViewModelFactory
import com.toharifqi.sportagain.ui.component.EmptyPlaceHolder
import com.toharifqi.sportagain.ui.component.SportFavoriteItem

@Composable
fun FavoriteScreen(
    context: Context,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideSportRepository(context))
    ),
    navigateToDetail: (SportDomainData) -> Unit
) {
    viewModel.getFavoriteSports()
    viewModel.sports.collectAsState(initial = emptyList()).value.let { sports ->
        FavoriteContent(
            sports = sports,
            navigateToDetail = navigateToDetail,
            onDeleteClick = { viewModel.deleteFavoriteSport(it) }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    sports: List<SportDomainData>,
    navigateToDetail: (SportDomainData) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    if (sports.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(bottom = 48.dp)
        ) {
            items(
                items = sports,
                key = { it.id }
            ) { sport ->
                SportFavoriteItem(
                    modifier = Modifier.animateItemPlacement(tween(durationMillis = 200)),
                    sport = sport,
                    isFavorite = true,
                    onClick = {
                        navigateToDetail(sport)
                    },
                    onFavoriteClick = { onDeleteClick(sport.id) }
                )
            }
        }
    } else EmptyPlaceHolder()
}
