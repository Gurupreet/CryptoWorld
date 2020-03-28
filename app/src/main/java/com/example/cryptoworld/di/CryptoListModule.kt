package com.example.cryptoworld.di

import androidx.lifecycle.ViewModel
import com.example.cryptoworld.ui.CryptoListFragment
import com.example.cryptoworld.ui.CryptoListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CryptoListModule {
    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun CryptoListFragment(): CryptoListFragment

    @Binds
    @IntoMap
    @ViewModelKey(CryptoListViewModel::class)
    abstract fun bindViewModel(viewmodel: CryptoListViewModel): ViewModel
}