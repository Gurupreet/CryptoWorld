package com.example.cryptoworld.data.remote

import com.example.cryptoworld.data.BaseDataSource
import com.example.cryptoworld.models.Crypto
import com.example.cryptoworld.models.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteDataSource(val apiService: ApiService, val ioDispatcher: CoroutineDispatcher): BaseDataSource  {

    override suspend fun getTopVolumeList(): Result<List<Crypto>> = withContext(ioDispatcher) {
        return@withContext try {
            val apiResponse = apiService.getRepositoryAsync().await()
            if (apiResponse.isSuccessful) {
                Result.Success(apiResponse.body() ?: arrayListOf<Crypto>())
            } else {
                Result.Error(apiResponse.message())
            }
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }


}