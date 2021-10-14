package com.lutfiseptian.test.kanggo.di

import com.lutfiseptian.test.kanggo.data.source.MovieDataSource
import org.koin.dsl.module

val DataSourceModule = module {
    single { MovieDataSource(get()) }
}