package com.toharifqi.sportagain.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toharifqi.sportagain.core.SportRepository
import com.toharifqi.sportagain.core.domain.SportDomainData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: SportRepository
) : ViewModel() {
    private val mutableSports: MutableStateFlow<List<SportDomainData>> =
        MutableStateFlow(emptyList())
    val sports: StateFlow<List<SportDomainData>>
        get() = mutableSports

    fun getFavoriteSports() {
        viewModelScope.launch {
            repository.getFavoriteSports()
                .collect {
                    mutableSports.value = it
                }
        }
    }

    fun deleteFavoriteSport(sportId: String) = viewModelScope.launch {
        repository.deleteFavoriteSport(sportId)
    }
}
