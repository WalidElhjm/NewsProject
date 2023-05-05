package com.example.newsapp.data.remote

import com.example.newsapp.common.Constants
import com.example.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("top-headlines")
    suspend fun getTopArticles(
        @Query("country") country: String,
        @Query("apiKey")
        apiKey: String = Constants.apiKey
    ): NewsResponse


}