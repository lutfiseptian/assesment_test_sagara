package com.lutfiseptian.test.kanggo.di

import com.lutfiseptian.test.kanggo.data.repositori.LocalRepo
import com.lutfiseptian.test.kanggo.data.repositori.RemoteRepo
import org.koin.dsl.module

val repoModule = module {
    single { RemoteRepo(get(),get(),get()) }
    single { LocalRepo(get(),get()) }
}