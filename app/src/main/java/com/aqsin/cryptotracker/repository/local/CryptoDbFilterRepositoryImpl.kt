package com.aqsin.cryptotracker.repository.local

import com.aqsin.cryptotracker.data.model.CryptoFilterEntity
import com.aqsin.cryptotracker.db.CryptoFilterDao
import javax.inject.Inject

//Inject FilterDao

class CryptoDbFilterRepositoryImpl @Inject constructor(var filterDao: CryptoFilterDao) :
    CryptoDbFilterRepository {
    override suspend fun getAllCryptoFilter(): List<CryptoFilterEntity> {
        return filterDao.getAllCryptoFilter()
    }

    override suspend fun getCryptoFilterByName(filterName: String): CryptoFilterEntity {
        return filterDao.getCryptoFilterByName(filterName)
    }

    override suspend fun insert(crypto: CryptoFilterEntity) {
        return filterDao.insert(crypto)
    }
}

// or Inject directly Room

/*
class CryptoDbFilterRepositoryImpl @Inject constructor(var roomDb: RoomDB) : CryptoDbFilterRepository {
    override suspend fun getAllCryptoFilter(): List<CryptoFilterEntity> {
        return roomDb.cryptoFilterDao().getAllCryptoFilter()
    }

    override suspend fun getCryptoFilterByName(filterName: String): CryptoFilterEntity {
        return roomDb.cryptoFilterDao().getCryptoFilterByName(filterName)
    }

    override suspend fun insert(crypto: CryptoFilterEntity) {
        return roomDb.cryptoFilterDao().insert(crypto)
    }
}

*/
