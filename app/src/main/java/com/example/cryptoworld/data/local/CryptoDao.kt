package com.example.cryptoworld.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.cryptoworld.models.Crypto

@Dao
interface CryptoDao {

    @Query("SELECT * FROM crypto")
    suspend fun getCryptoList(): List<Crypto>

}