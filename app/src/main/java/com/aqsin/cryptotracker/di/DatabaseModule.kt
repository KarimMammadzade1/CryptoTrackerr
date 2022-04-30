package com.aqsin.cryptotracker.di

import android.content.Context
import androidx.room.Room
import com.aqsin.cryptotracker.db.RoomDB
import com.aqsin.helpers.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RoomDB::class.java, Constants.DatabaseName
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun getFilterDao(db: RoomDB) = db.cryptoFilterDao()

    @Singleton
    @Provides
    fun getHistoryDao(db : RoomDB) = db.cryptoHistoryDao()
}

