package com.example.newsapp.domain.repository

import com.example.newsapp.common.DataState
import com.example.newsapp.data.model.NewsResponse



interface NewsRepository {
    suspend fun getArticles():DataState<NewsResponse>
}