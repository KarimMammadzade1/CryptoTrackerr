package com.aqsin.cryptotracker.di
//
import com.aqsin.cryptotracker.repository.local.CryptoDbFilterRepository
import com.aqsin.cryptotracker.repository.local.CryptoDbFilterRepositoryImpl
import com.aqsin.cryptotracker.repository.local.CryptoDbHistoryRepository
import com.aqsin.cryptotracker.repository.local.CryptoDbHistoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DBRepositoryModule {

    @Binds
    abstract fun getFilterCryptoRepository(repository : CryptoDbFilterRepositoryImpl) : CryptoDbFilterRepository

    @Binds
    abstract fun getHistoryCryptoRepository(repository : CryptoDbHistoryRepositoryImpl) : CryptoDbHistoryRepository

}