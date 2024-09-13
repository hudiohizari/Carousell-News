package com.hizari.common.util

sealed class Resources<T>(val data: T?= null, val throwable: Throwable?= null) {

    class Success<T>(data: T): Resources<T>(data)
    
    class Empty<T>: Resources<T>()

    class Loading<T>: Resources<T>()

    class Error<T>(throwable: Throwable): Resources<T>(throwable = throwable)

    fun isLoading(): Boolean {
        return this is Loading<*>
    }

    fun isFailed(): Boolean {
        return this is Error<*>
    }

    fun isSuccess(): Boolean {
        return this is Success<*>
    }

    fun isLoaded(): Boolean {
        return !isLoading()
    }

}
