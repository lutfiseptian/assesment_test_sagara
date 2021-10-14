package com.lutfiseptian.test.kanggo.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.lutfiseptian.test.kanggo.data.source.MovieDataSource
import com.lutfiseptian.test.kanggo.data.state.MovieState
import com.lutfiseptian.test.kanggo.model.DataMovie

class MovieDataFactory(
    private val movieDataSource: MovieDataSource
): DataSource.Factory<Int, DataMovie>() {


    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return movieDataSource.also {
            it.liveData = liveData
        }
    }

}