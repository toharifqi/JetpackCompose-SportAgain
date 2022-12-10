package com.toharifqi.sportagain.di

import android.content.Context
import com.toharifqi.sportagain.core.SportRepository
import com.toharifqi.sportagain.core.local.room.SportDatabase
import com.toharifqi.sportagain.core.remote.retrofit.ApiConfig
import kotlinx.coroutines.Dispatchers

object Injection {
    fun provideSportRepository(context: Context): SportRepository {
        val apiService = ApiConfig.getApiService()
        val database = SportDatabase.getDatabase(context)
        val dao = database.sportDao()
        val dispatcher = Dispatchers.IO
        return SportRepository.getInstance(apiService, dao, dispatcher)
    }
}
