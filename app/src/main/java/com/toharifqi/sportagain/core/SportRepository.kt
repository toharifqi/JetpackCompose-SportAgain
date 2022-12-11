package com.toharifqi.sportagain.core

import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.core.local.entity.SportEntity
import com.toharifqi.sportagain.core.local.room.SportDao
import com.toharifqi.sportagain.core.remote.retrofit.ApiService
import com.toharifqi.sportagain.ui.common.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SportRepository(
    private val apiService: ApiService,
    private val sportDao: SportDao,
    private val dispatcher: CoroutineDispatcher
) {
    fun getAllSports(): Flow<UiState<List<SportDomainData>>> = flow {
        try {
            val responses = apiService.getList()
            val sports = responses.sports.map {
                SportDomainData(it)
            }
            emit(UiState.Success(sports))
        } catch (e: Exception) {
            emit(UiState.Error(e.message))
        }
    }.flowOn(dispatcher)

    fun getFavoriteSports() = sportDao.getFavoriteSports().map { entities ->
        entities.map {
            SportDomainData(it)
        }
    }.flowOn(dispatcher)

    suspend fun setSportFavorite(sport: SportDomainData) = withContext(dispatcher) {
        sportDao.setSportFavorite(SportEntity(sport))
    }

    suspend fun deleteFavoriteSport(sportId: String) = withContext(dispatcher) {
        sportDao.deleteSport(sportId)
    }

    fun isSportFavorite(sportId: String) = sportDao.isSportFavorite(sportId).flowOn(dispatcher)

    companion object {
        @Volatile
        private var instace: SportRepository? = null
        fun getInstance(
            apiService: ApiService,
            dao: SportDao,
            dispatcher: CoroutineDispatcher
        ): SportRepository =
            instace ?: synchronized(this) {
                instace ?: SportRepository(apiService, dao, dispatcher)
            }.also { instace = it }
    }
}
