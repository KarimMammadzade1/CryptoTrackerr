package com.aqsin.cryptotracker.data.client

import com.aqsin.cryptotracker.data.api.CryptoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun baseUrl() = "https://api.coingecko.com"

    fun getRetrofit(url : String) =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getCryptoApi(retrofit: Retrofit) : CryptoApi = retrofit.create(CryptoApi::class.java)
}