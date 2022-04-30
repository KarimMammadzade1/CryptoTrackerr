package com.aqsin.cryptotracker.repository.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aqsin.cryptotracker.data.model.CryptoFilterEntity

interface CryptoDbFilterRepository {

    suspend fun getAllCryptoFilter() : List<CryptoFilterEntity>
    suspend fun getCryptoFilterByName(filterName : String) : CryptoFilterEntity
    suspend fun insert(crypto: CryptoFilterEntity)

}