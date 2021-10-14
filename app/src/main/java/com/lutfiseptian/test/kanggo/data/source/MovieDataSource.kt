package com.lutfiseptian.test.kanggo.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.lutfiseptian.test.kanggo.data.state.MovieState
import com.lutfiseptian.test.kanggo.model.DataMovie
import com.lutfiseptian.test.kanggo.network.ApiService
import io.reactivex.disposables.CompositeDisposable

class MovieDataSource(private val apiService: ApiService) : PageKeyedDataSource<Int, DataMovie>(){

    lateinit var liveData: MutableLiveData<MovieState>

    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataMovie>
    ) {
        apiService.getAllMovie("popular",1)
            .map<MovieState> {
                callback.onResult(it.data.toMutableList(),1,2)
                MovieState.Result(it)
            }
            .onErrorReturn(MovieState::Error)
            .toFlowable()
            .startWith(MovieState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }



    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {
        apiService.getAllMovie("popular",params.key)
            .map<MovieState> {
                callback.onResult(it.data.toMutableList(),params.key + 1)
                MovieState.Result(it)
            }
            .onErrorReturn(MovieState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {

    }

}