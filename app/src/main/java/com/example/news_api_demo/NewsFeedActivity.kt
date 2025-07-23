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
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)//设置布局管理器 瀑布流
        adapter = NewsAdapter()//创建适配器
        recyclerView.adapter = adapter//绑定适配器

        val repository = Repository(RetrofitClient.api)//创建数据仓库对象
        viewModel = NewsViewModel(repository)//延迟初始化viewmodel
        viewModel.newsList.observe(this) { newsItems ->
            adapter.submitList(newsItems.articles)
        }
//        通过 observe 订阅 newsList 数据
//        当新闻数据发生变化时 自动调用 lambda 表达式 把新数据提交给 Adapter 刷新界面
        viewModel.fetchTopNews("us", "08928b7fed414010820d9af990c05f89")
    }
}