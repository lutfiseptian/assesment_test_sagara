package com.lutfiseptian.test.kanggo.di

import androidx.paging.PagedList
import com.lutfiseptian.test.kanggo.database.DbRoom
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        PagedList.Config.Builder()
            .setPageSize(1)
            .setInitialLoadSizeHint(2)
            .setPrefetchDistance(1)
            .setEnablePlaceholders(true)
            .build()
    }

    single { DbRoom(androidContext()) }


}