package com.lutfiseptian.test.kanggo.di

import com.lutfiseptian.test.kanggo.ui.detail.DetailViewModel
import com.lutfiseptian.test.kanggo.ui.favorite.FavouriteViewModel
import com.lutfiseptian.test.kanggo.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get(),get()) }
    viewModel { FavouriteViewModel(get()) }
}