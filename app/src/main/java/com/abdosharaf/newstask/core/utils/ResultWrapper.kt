package com.abdosharaf.newstask.core.utils

sealed class ResultWrapper<T>(val data: T? = null) {
    class Loading<T>(data: T? = null) : ResultWrapper<T>(data)
    class Success<T>(data: T?) : ResultWrapper<T>(data)
    class Failure<T>(data: T? = null) : ResultWrapper<T>(data)
    class NoInternet<T>(data: T? = null) : ResultWrapper<T>(data)
}