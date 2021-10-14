package com.lutfiseptian.test.kanggo.di

import com.lutfiseptian.test.kanggo.data.factory.Factory
import com.lutfiseptian.test.kanggo.data.factory.MovieDataFactory
import org.koin.dsl.module

val FactoryModule = module {
    factory { Factory(get()) }
    factory { MovieDataFactory(get()) }
}