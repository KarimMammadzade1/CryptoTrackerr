package com.aqsin.cryptotracker.repository.local

import com.aqsin.cryptotracker.data.model.CryptoEntity

interface CryptoDbHistoryRepository{
    suspend fun getAllHistory() : List<CryptoEntity>
    suspend fun insert(historyList: CryptoEntity)
}