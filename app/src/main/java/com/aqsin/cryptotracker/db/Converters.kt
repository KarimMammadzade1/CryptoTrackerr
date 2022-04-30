package com.aqsin.cryptotracker.db

import androidx.room.TypeConverter
import com.aqsin.cryptotracker.data.model.Crypto
import com.aqsin.cryptotracker.data.model.Rate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RateConverter {
    val gson = Gson()
    val type = object : TypeToken<List<Rate>>() {}.type

    @TypeConverter
    fun fromRateList(data : ArrayList<Rate>) = gson.toJson(data, type)

    @TypeConverter
    fun toRateList(data : String) = gson.fromJson<ArrayList<Rate>>(data, type)
}

class CryptoConverter {
    val gson = Gson()
    val type = object : TypeToken<List<Crypto>>() {}.type

    @TypeConverter
    fun fromCryptoList(data : ArrayList<Crypto>) = gson.toJson(data, type)

    @TypeConverter
    fun toCryptoList(data : String) = gson.fromJson<ArrayList<Crypto>>(data, type)
}
