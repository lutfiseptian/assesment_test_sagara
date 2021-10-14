package com.lutfiseptian.test.kanggo.data.state

import com.lutfiseptian.test.kanggo.model.ResponseMovie

sealed class MovieState {
    object Loading : MovieState()
    data class Result(val data : ResponseMovie) : MovieState()
    data class Error(val error : Throwable) : MovieState()
}