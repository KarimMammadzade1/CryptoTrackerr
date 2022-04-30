package com.aqsin.cryptotracker.data.api

import com.aqsin.cryptotracker.data.model.Crypto
import com.aqsin.cryptotracker.data.model.CryptoType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


//ids=bitcoin,ethereum,tether&vs_currencies=usd
interface CryptoApi  {
    @GET("api/v3/simple/price")
    suspend fun getCrypto(@Query("ids") ids : String,
                 @Query("vs_currencies") currencies : String) : CryptoType
}