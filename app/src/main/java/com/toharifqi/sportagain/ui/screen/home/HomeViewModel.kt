package com.toharifqi.sportagain.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toharifqi.sportagain.core.SportRepository
import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SportRepository
) : ViewModel() {
    private val mutableUiState: MutableStateFlow<UiState<List<SportDomainData>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<SportDomainData>>>
        get() = mutableUiState

    fun getAllSports() {
        viewModelScope.launch {
            repository.getAllSports()
                .collect{
                    mutableUiState.value = it
                }
        }
    }
}
