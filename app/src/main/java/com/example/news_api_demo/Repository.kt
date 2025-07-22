package com.example.news_api_demo

class Repository(private val api: Top_ApiService){
    suspend fun getTopNews(country: String,apiKey: String):News_Items{
        return api.get_topNews(country,apiKey)
    }
}