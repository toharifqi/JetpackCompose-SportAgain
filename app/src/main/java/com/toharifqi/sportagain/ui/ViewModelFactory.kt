package com.toharifqi.sportagain.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toharifqi.sportagain.core.SportRepository
import com.toharifqi.sportagain.ui.screen.detail.DetailViewModel
import com.toharifqi.sportagain.ui.screen.favorite.FavoriteViewModel
import com.toharifqi.sportagain.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: SportRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
