package com.example.news_api_demo

import retrofit2.http.GET
import retrofit2.http.Query

interface Top_ApiService {
    @GET("top-headlines")
    suspend fun get_topNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ):News_Items
}

