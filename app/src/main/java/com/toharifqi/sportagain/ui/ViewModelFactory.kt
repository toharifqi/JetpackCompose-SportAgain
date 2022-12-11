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
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
