package com.lutfiseptian.test.kanggo.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lutfiseptian.test.kanggo.data.repositori.LocalRepo
import com.lutfiseptian.test.kanggo.data.repositori.RemoteRepo
import com.lutfiseptian.test.kanggo.data.state.DetailState
import com.lutfiseptian.test.kanggo.database.MovieEntity
import kotlinx.coroutines.*

class DetailViewModel (private val repository: RemoteRepo, private val localRepo: LocalRepo) : ViewModel() {

    val state : MutableLiveData<DetailState> by lazy {
        MutableLiveData<DetailState>()
    }

    val stateFavorite : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getDetailMovie(movieId: Int) {
        repository.getDetailData(movieId, state)
    }

    fun addToFavorite(data : MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { localRepo.checkDataMovie(data) }
            if(checkData.await().isEmpty()){
                localRepo.addDataMovie(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                localRepo.deleteDataMovie(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }

    fun checkFavorite(data : MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { localRepo.checkDataMovie(data) }
            if(checkData.await().isNotEmpty()){
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }
}