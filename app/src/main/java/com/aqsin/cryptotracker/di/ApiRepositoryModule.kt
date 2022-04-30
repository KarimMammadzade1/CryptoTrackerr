package com.aqsin.cryptotracker.di

import com.aqsin.cryptotracker.repository.remote.CryptoApiRepositoryImpl
import com.aqsin.cryptotracker.repository.remote.CryptoApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiRepositoryModule {

    @Binds
    abstract fun getCryptoRepository(impl : CryptoApiRepositoryImpl) : CryptoApiRepository
}