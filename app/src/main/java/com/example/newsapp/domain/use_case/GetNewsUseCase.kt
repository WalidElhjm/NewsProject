package com.example.newsapp.domain.use_case

import com.example.newsapp.common.DataState
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,

) {
    operator fun invoke(): Flow<DataState<NewsResponse?>> = flow {
        try {
            emit(DataState.Loading())
            val articles= repository.getArticles().data
            if(articles != null){
                emit(DataState.Success(articles))
            }else
            {
                emit(DataState.Error(Exception()))
            }
        } catch (e: Exception) {
        }catch (e : IOException){
            emit(DataState.Error(e))

        }

    }
}