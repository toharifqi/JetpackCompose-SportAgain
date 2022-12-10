package com.toharifqi.sportagain.core.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toharifqi.sportagain.core.local.entity.SportEntity

@Database(
    entities = [SportEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SportDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao

    companion object {
        @Volatile
        private var INSTANCE: SportDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SportDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SportDatabase::class.java, "sport_db"
                ).fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}