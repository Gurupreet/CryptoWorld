package com.example.cryptoworld.repository

import com.example.cryptoworld.data.local.LocalDataSource
import com.example.cryptoworld.models.Crypto
import com.example.cryptoworld.models.Result
import com.example.cryptoworld.di.ApplicationModule
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @ApplicationModule.LocalDataSource private val localDataSource: LocalDataSource,
    @ApplicationModule.RemoteDataSource private val remoteDataSource: ApplicationModule.RemoteDataSource
) : MainRepository {

    override suspend fun getCryptoList(): Result<List<Crypto>> {
        return localDataSource.getTopVolumeList()
    }

    override suspend fun getCryptoDetail(symbol: String): Result<Crypto> {
        return  Result.Error("error")
    }
}