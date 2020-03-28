package com.example.cryptoworld.data

import com.example.cryptoworld.models.Crypto
import com.example.cryptoworld.models.Result

interface BaseDataSource {
    suspend fun getTopVolumeList(): Result<List<Crypto>>
}