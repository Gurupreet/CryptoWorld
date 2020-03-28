package com.example.cryptoworld.di

import androidx.lifecycle.ViewModel
import com.example.cryptoworld.ui.news.NewsFragment
import com.example.cryptoworld.ui.news.NewsViewModel
import dagger.Binds
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

abstract class NewsModule {

    @ContributesAndroidInjector(modules = [ViewModelFactory::class])
    internal abstract fun NewsFragment(): NewsFragment

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindViewModel(viewModel: NewsViewModel): ViewModel
}