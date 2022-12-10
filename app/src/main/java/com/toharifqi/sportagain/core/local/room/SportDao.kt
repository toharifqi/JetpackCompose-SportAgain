package com.toharifqi.sportagain.core.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toharifqi.sportagain.core.local.entity.SportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao {
    @Query("SELECT * FROM sports")
    fun getFavoriteSports(): Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setSportFavorite(sport: SportEntity)

    @Query("DELETE FROM sports WHERE sportId = :sportId")
    suspend fun deleteSport(sportId: String)

    @Query("SELECT EXISTS(SELECT * FROM sports WHERE sportId = :sportId)")
    fun isSportFavorite(sportId: String): Flow<Boolean>
}