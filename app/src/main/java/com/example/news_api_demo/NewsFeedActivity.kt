package com.example.news_api_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class NewsFeedActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed)

        val recyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = NewsAdapter()
        recyclerView.adapter = adapter

        val repository = Repository(RetrofitClient.api)
        viewModel = NewsViewModel(repository)
        viewModel.newsList.observe(this) { newsItems ->
            adapter.submitList(newsItems.articles)
        }
        viewModel.fetchTopNews("us", "08928b7fed414010820d9af990c05f89")
    }
}