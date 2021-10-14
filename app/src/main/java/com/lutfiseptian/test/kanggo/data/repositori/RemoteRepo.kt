package com.lutfiseptian.test.kanggo.data.repositori

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lutfiseptian.test.kanggo.data.factory.Factory
import com.lutfiseptian.test.kanggo.data.state.DetailState
import com.lutfiseptian.test.kanggo.data.state.MovieState
import com.lutfiseptian.test.kanggo.model.DataMovie
import com.lutfiseptian.test.kanggo.network.ApiService
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoteRepo(
    private val apiService: ApiService,
    private val config : PagedList.Config,
    private val factory : Factory
) {

    private val disposable by lazy {
        CompositeDisposable()
    }
//    var disposable: CompositeDisposable = CompositeDisposable()
//
//    fun getDisposible(): CompositeDisposable = disposable

    fun getMovieData(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.movieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }

    }

    fun getDetailData(movieId: Int, callback: MutableLiveData<DetailState>) {
        apiService.getDetailMovie(movieId)
            .map<DetailState>(DetailState::Result)
            .onErrorReturn(DetailState::Error)
            .toFlowable()
            .startWith(DetailState.Loading)
            .subscribe(callback::postValue)
            .let { return@let disposable::add }
    }
}