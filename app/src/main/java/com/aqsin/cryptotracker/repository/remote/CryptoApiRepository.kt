package com.aqsin.cryptotracker.repository.remote

import com.aqsin.cryptotracker.data.model.CryptoType
import kotlinx.coroutines.flow.Flow

interface CryptoApiRepository {
    suspend fun getCrypto(ids : String,
                          currencies : String) : Flow<CryptoType>
}