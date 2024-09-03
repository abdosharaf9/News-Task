package com.abdosharaf.newstask.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

suspend fun <T> safeCall(call: suspend () -> T): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading)
    try {
        emit(ResultWrapper.Success(data = call.invoke()))
    } catch (e: IOException) {
        e.printStackTrace()
        emit(ResultWrapper.NoInternet)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        emit(ResultWrapper.Failure(message = throwable.message.toString()))
    }
}