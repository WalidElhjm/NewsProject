package com.example.newsapp.data.repository

import com.example.newsapp.common.DataState
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.domain.common.makeSafeRequest
import com.example.newsapp.domain.repository.NewsRepository
import java.util.*

import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    private var deviceLang: String = Locale.getDefault().country

    override suspend fun getArticles(): DataState<NewsResponse> {
       return makeSafeRequest{
           api.getTopArticles(deviceLang)
        }

    }
}