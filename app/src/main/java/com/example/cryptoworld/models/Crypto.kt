package com.example.cryptoworld.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Crypto")
data class Crypto(
    val name: String,
    @PrimaryKey
    val symbol: String,
    val price: Double,
    val logo: String)