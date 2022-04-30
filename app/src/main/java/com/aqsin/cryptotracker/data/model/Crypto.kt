package com.aqsin.cryptotracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aqsin.helpers.adapters.UtilityHelper


// Api Json Response
typealias RateType = Map<String,Float>
typealias CryptoType = Map<String,RateType>


//  Dynamic Keys convert my model
fun CryptoType.toModel(): CryptoModel {
    val crypto = this.keys.map {
        val rates = this[it]?.map {
            Rate(currency = it.key, rate = it.value)
        } ?: arrayListOf()
        Crypto(name = it,rates = ArrayList(rates))
    }
    return CryptoModel(date = UtilityHelper.getTodayString(), ArrayList(crypto))
}

fun CryptoModel.toEntity() = CryptoEntity(date = this.date, crypto = this.crypto)
fun CryptoEntity.toModel() = CryptoModel(date = this.date, crypto = this.crypto)
fun List<CryptoEntity>.toModelList() = this.map {
    CryptoModel(date = it.date, crypto = it.crypto)
}


@Entity(tableName = "crypto_history")
data class CryptoEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val date : String,
    val crypto : ArrayList<Crypto>
)

data class CryptoModel(
    val date : String,
    val crypto : ArrayList<Crypto>
)

data class Crypto(
    val name : String,
    val rates : ArrayList<Rate>
)

data class Rate(
    val currency : String,
    val rate : Float
)
