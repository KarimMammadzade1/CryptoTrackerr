package com.aqsin.cryptotracker.repository.remote

import com.aqsin.cryptotracker.data.api.CryptoApi
import com.aqsin.cryptotracker.data.model.CryptoType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoApiRepositoryImpl @Inject constructor(var api : CryptoApi) : CryptoApiRepository {
    override suspend fun getCrypto(ids: String, currencies: String): Flow<CryptoType> {
        return flow {
            emit(api.getCrypto(ids,currencies))
        }
    }
}