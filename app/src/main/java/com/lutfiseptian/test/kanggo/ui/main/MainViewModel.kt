package com.lutfiseptian.test.kanggo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lutfiseptian.test.kanggo.data.repositori.RemoteRepo
import com.lutfiseptian.test.kanggo.data.state.MovieState
import com.lutfiseptian.test.kanggo.model.DataMovie

class MainViewModel(private val repository: RemoteRepo): ViewModel(){

    val callback : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val data : MutableLiveData<PagedList<DataMovie>> by lazy {
        MutableLiveData<PagedList<DataMovie>>()
    }

    fun getMovieData(){
        repository.getMovieData(callback, data)
    }
}