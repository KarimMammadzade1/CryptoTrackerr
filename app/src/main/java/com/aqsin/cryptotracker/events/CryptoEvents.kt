package com.aqsin.cryptotracker.events

import com.aqsin.cryptotracker.data.model.CryptoModel
import com.aqsin.cryptotracker.data.model.CryptoFilterEntity

sealed class CryptoUiEvents {
    data class LoadCrypto(val data : CryptoModel?) : CryptoUiEvents()
    data class LoadHistory(val history: List<CryptoModel>) : CryptoUiEvents()
    data class OnFilter(val data : CryptoFilterEntity) : CryptoUiEvents()
    data class OnMessage(val data : String) : CryptoUiEvents()
}