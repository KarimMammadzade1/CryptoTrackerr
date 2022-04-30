package com.aqsin.cryptotracker.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aqsin.cryptotracker.data.client.RetrofitClient
import com.aqsin.cryptotracker.data.model.*
import com.aqsin.cryptotracker.db.RoomDB
import com.aqsin.helpers.Constants
import com.aqsin.helpers.notification.NotificationHelper

class CryptoTrackerWork(context : Context,workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val client = RetrofitClient.getCryptoApi(RetrofitClient.getRetrofit(RetrofitClient.baseUrl()))
        val cryptos = client.getCrypto(Constants.CryptoNames,Constants.CryptoCurrency)
        val model = cryptos.toModel()
        val db = RoomDB.getDb(applicationContext)
        db.cryptoHistoryDao().insert(model.toEntity())
        val filterList = db.cryptoFilterDao().getAllCryptoFilter()
        showNotification(model,filterList)
        return Result.success()
    }

    private fun showNotification(crypto: CryptoModel?, filterList: List<CryptoFilterEntity>?){
        crypto?.crypto?.forEach { currentCrypto ->
            val currentCyrptoUsdRate = currentCrypto.rates.find { it.currency == "usd" }
            val usdRate = filterList?.find { it.name == currentCrypto.name }
            takeIf {
                    usdRate != null && currentCyrptoUsdRate != null
            }.apply {
                when{
                    currentCyrptoUsdRate?.rate!! > usdRate!!.maxValue -> {
                            NotificationHelper.makePushNotification("${currentCrypto.name.uppercase()} " +
                                    "Reach Max","Current ${currentCyrptoUsdRate?.rate}, " +
                                    "Max ${usdRate!!.maxValue}",applicationContext)
                    }
                        currentCyrptoUsdRate?.rate!! < usdRate!!.minValue -> {
                            NotificationHelper.makePushNotification("${currentCrypto.name.uppercase()} " +
                                    "Reach Min","Current ${currentCyrptoUsdRate?.rate}, " +
                                    "Min ${usdRate!!.minValue}",applicationContext)
                   }
                }
            }
        }
    }
}