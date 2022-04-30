package com.aqsin.cryptotracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aqsin.cryptotracker.data.model.CryptoFilterEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CryptoFilterDao{

    @Query("select * from crypto_filter")
    suspend fun getAllCryptoFilter() : List<CryptoFilterEntity>

    @Query("select * from crypto_filter where name == :filterName")
    suspend fun getCryptoFilterByName(filterName : String) : CryptoFilterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crypto: CryptoFilterEntity)
}