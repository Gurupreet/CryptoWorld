package com.example.cryptoworld.repository

import com.example.cryptoworld.models.Crypto
import com.example.cryptoworld.models.Result

interface MainRepository {

    suspend fun getCryptoList() : Result<List<Crypto>>

    suspend fun getCryptoDetail(symbol: String) : Result<Crypto>

}