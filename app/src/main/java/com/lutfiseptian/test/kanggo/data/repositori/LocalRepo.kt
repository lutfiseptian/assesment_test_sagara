package com.lutfiseptian.test.kanggo.data.repositori

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lutfiseptian.test.kanggo.database.DbRoom
import com.lutfiseptian.test.kanggo.database.MovieEntity

class LocalRepo(
    private val database: DbRoom,
    private val config: PagedList.Config
) {

    fun getFavoriteData(data: MutableLiveData<PagedList<MovieEntity>>) {
        LivePagedListBuilder(
            database.movie().getData(),
            config
        ).build().observeForever(data::postValue)
    }


    fun addDataMovie(data: MovieEntity) {
        database.movie().add(data)
    }

    fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        return database.movie().getDataById(data.id)
    }

    fun deleteDataMovie(data: MovieEntity) {
        database.movie().delete(data)
    }

    fun getDatabase(): DbRoom = database
}