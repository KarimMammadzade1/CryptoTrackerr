package com.aqsin.cryptotracker.db

import android.content.Context
import androidx.room.*
import com.aqsin.cryptotracker.data.model.CryptoEntity
import com.aqsin.cryptotracker.data.model.CryptoFilterEntity
import com.aqsin.helpers.Constants


@Database(entities = arrayOf(CryptoFilterEntity::class, CryptoEntity::class), version = 4)
@TypeConverters(CryptoConverter::class, RateConverter::class)
abstract class RoomDB : RoomDatabase() {

        abstract fun cryptoFilterDao(): CryptoFilterDao
        abstract fun cryptoHistoryDao(): CryptoHistoryDao

        companion object{
                private var INSTANCE : RoomDB? = null

                fun getDb(context : Context) : RoomDB {
                        return INSTANCE ?: run {
                                Room.databaseBuilder(
                                        context,
                                        RoomDB::class.java, Constants.DatabaseName
                                )
                                .fallbackToDestructiveMigration()
                                .build().also {
                                                INSTANCE = it
                                        }
                        }
                }
        }
}
