package com.example.cryptoworld.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoworld.models.Crypto

@Database(
    version = 1,
    entities = [Crypto::class]
)
//@TypeConverters(ListTypeConverter::class)
abstract class MainDatabase: RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao


    companion object {
        private const val DATABASE_NAME = "main.db"
        private var instance: MainDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MainDatabase::class.java, DATABASE_NAME
            ).build()
    }
}