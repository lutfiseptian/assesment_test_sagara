package com.lutfiseptian.test.kanggo.data.state

import com.lutfiseptian.test.kanggo.model.detailmovie.ResponseDetailMovie

sealed class DetailState {
    object Loading : DetailState()
    data class Result(val data : ResponseDetailMovie) : DetailState()
    data class Error(val error : Throwable) : DetailState()
}