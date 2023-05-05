package com.example.newsapp.domain.common

import com.example.newsapp.common.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeSafeRequest(
    execute: suspend () -> T
): DataState<T> {
    return withContext(Dispatchers.IO) {
        try {
            DataState.Success(execute())
        } catch (e: Exception) {
            DataState.Error(e)
        }
    }
}