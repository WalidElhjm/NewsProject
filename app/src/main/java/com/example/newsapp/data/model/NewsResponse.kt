package com.example.newsapp.data.model


data class NewsResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<NewsArticle>? = null
)
