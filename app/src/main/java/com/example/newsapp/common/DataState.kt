package com.example.newsapp.common



sealed class DataState<T>(
    val data: T? = null,
    val message: Exception? = null
) {
    class Success<T>(data: T) : DataState<T>(data)
    class Error<T>(message: Exception) : DataState<T>(message = message)
    class Loading<T> : DataState<T>()
}