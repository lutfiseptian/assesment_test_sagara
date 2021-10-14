package com.lutfiseptian.test.kanggo.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lutfiseptian.test.kanggo.data.repositori.LocalRepo
import com.lutfiseptian.test.kanggo.database.MovieEntity

class FavouriteViewModel (
    private val repository: LocalRepo
) : ViewModel() {

    val data : MutableLiveData<PagedList<MovieEntity>> by lazy {
        MutableLiveData<PagedList<MovieEntity>>()
    }

    fun getFavoriteMovie(){
        repository.getFavoriteData(data)
    }
}