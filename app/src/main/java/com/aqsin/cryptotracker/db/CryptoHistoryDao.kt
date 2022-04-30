package com.aqsin.cryptotracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aqsin.cryptotracker.data.model.CryptoEntity


@Dao
interface  CryptoHistoryDao {
    @Query("select * from crypto_history order by id DESC")
    suspend fun getAllHistory() : List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyList: CryptoEntity)
}