package com.toharifqi.sportagain.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toharifqi.sportagain.core.SportRepository
import com.toharifqi.sportagain.core.domain.SportDomainData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: SportRepository
) : ViewModel() {

    private val mutableFavoriteStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val favoriteStatus: StateFlow<Boolean>
        get() = mutableFavoriteStatus

    fun setSportId(id: String) = viewModelScope.launch {
        repository.isSportFavorite(id).collect {
            mutableFavoriteStatus.value = it
        }
    }

    fun changeFavorite(sport: SportDomainData) = viewModelScope.launch {
        if (mutableFavoriteStatus.value) {
            repository.deleteFavoriteSport(sport.id)
        } else {
            repository.setSportFavorite(sport)
        }
    }
}
