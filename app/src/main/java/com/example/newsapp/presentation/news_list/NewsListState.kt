package com.example.newsapp.presentation.news_list

import com.example.newsapp.data.model.NewsArticle

data class NewsListState(
    val isLoading: Boolean = false,
    val articles: List<NewsArticle> = emptyList(),
    val error: Exception? = null,
    )