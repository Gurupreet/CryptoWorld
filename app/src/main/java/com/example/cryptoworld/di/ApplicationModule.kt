/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cryptoworld.di

import android.content.Context
import androidx.room.Room
import com.example.cryptoworld.data.BaseDataSource
import com.example.cryptoworld.data.local.LocalDataSource
import com.example.cryptoworld.data.local.MainDatabase
import com.example.cryptoworld.data.remote.ApiService
import com.example.cryptoworld.data.remote.RemoteDataSource
import com.example.cryptoworld.repository.MainRepository
import com.example.cryptoworld.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME


@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(RUNTIME)
    annotation class LocalDataSource

    @JvmStatic
    @Singleton
    @RemoteDataSource
    @Provides
    fun provideRemoteDataSource(apiService: ApiService, ioDispatcher: CoroutineDispatcher): BaseDataSource {
        return RemoteDataSource(apiService, ioDispatcher)
    }

    @JvmStatic
    @Singleton
    @LocalDataSource
    @Provides
    fun providesLocalDataSource(
        database: MainDatabase,
        ioDispatcher: CoroutineDispatcher
    ): BaseDataSource {
        return LocalDataSource(
            database.cryptoDao(), ioDispatcher
        )
    }
//
    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context) = MainDatabase.getInstance(context)

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @JvmStatic
    @Singleton
    @Provides
    fun providesApiService() = ApiService.invoke()
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: MainRepositoryImpl): MainRepository
}
