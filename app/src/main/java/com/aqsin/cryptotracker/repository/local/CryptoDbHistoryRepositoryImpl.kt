package com.aqsin.cryptotracker.repository.local

import com.aqsin.cryptotracker.data.model.CryptoEntity
import com.aqsin.cryptotracker.db.CryptoHistoryDao
import javax.inject.Inject

//Inject HistoryDao

class CryptoDbHistoryRepositoryImpl @Inject constructor(var historyDao: CryptoHistoryDao)  :
    CryptoDbHistoryRepository {
    override suspend fun getAllHistory(): List<CryptoEntity> {
        return historyDao.getAllHistory()
    }

    override suspend fun insert(historyList: CryptoEntity) {
        historyDao.insert(historyList)
    }
}


// Or inject directly room

/*
class CryptoDbHistoryRepositoryImpl @Inject constructor(var roomDb: RoomDB)  : CryptoDbHistoryRepository {
    override suspend fun getAllHistory(): List<CryptoEntity> {
        return roomDb.cryptoHistoryDao().getAllHistory()
    }

    override suspend fun insert(historyList: CryptoEntity) {
        roomDb.cryptoHistoryDao().insert(historyList)
    }
}

*/