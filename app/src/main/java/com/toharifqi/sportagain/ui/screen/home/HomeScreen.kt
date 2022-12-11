package com.toharifqi.sportagain.ui.screen.home

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.di.Injection
import com.toharifqi.sportagain.ui.ViewModelFactory
import com.toharifqi.sportagain.ui.common.UiState
import com.toharifqi.sportagain.ui.component.ErrorIndicator
import com.toharifqi.sportagain.ui.component.LoadingIndicator
import com.toharifqi.sportagain.ui.component.SportItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideSportRepository(context))
    ),
    navigateToDetail: (SportDomainData) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                LoadingIndicator()
                viewModel.getAllSports()
            }
            is UiState.Success -> {
                HomeContent(
                    modifier = modifier,
                    sports = state.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error   -> {
                ErrorIndicator()
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    sports: List<SportDomainData>,
    navigateToDetail: (SportDomainData) -> Unit
) {
    if (sports.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
        ) {
            items(sports) { sport ->
                SportItem(
                    sport = sport,
                    onClick = {
                        navigateToDetail(sport)
                    }
                )
            }
        }
    } else ErrorIndicator()
}
