package com.example.cryptoworld.data.local

import com.example.cryptoworld.data.BaseDataSource
import com.example.cryptoworld.models.Crypto
import com.example.cryptoworld.models.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class LocalDataSource internal constructor(
    private val cryptoDao: CryptoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) :BaseDataSource {

    override suspend fun getTopVolumeList(): Result<List<Crypto>> =  withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(cryptoDao.getCryptoList())
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

}