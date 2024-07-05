package com.ivantrykosh.app.spofidata.common

sealed class Resource<T> (val data: T? = null, val statusCode: Int? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(statusCode: Int, data: T? = null) : Resource<T>(data, statusCode)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}