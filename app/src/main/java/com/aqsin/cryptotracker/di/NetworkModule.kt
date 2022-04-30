package com.aqsin.cryptotracker.di

import com.aqsin.cryptotracker.data.api.CryptoApi
import com.aqsin.helpers.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule  {

    @Singleton
    @Provides
    fun getBaseUrl() = Constants.BaseUrl

    @Singleton
    @Provides
    fun getRetrofir(url : String) =
        Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : CryptoApi = retrofit.create(CryptoApi::class.java)


}