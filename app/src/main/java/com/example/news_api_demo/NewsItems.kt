package com.example.news_api_demos

data class News_Items(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: News_Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class News_Source(
    val id: String?,
    val name: String
)