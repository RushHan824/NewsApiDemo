package com.example.news_api_demo

class Repository(private val api: Top_ApiService){//数据仓库
    //作用是在这里写一下api调用方法的具体实现
    suspend fun getTopNews(country: String,apiKey: String):News_Items{
        return api.get_topNews(country,apiKey)
    }
}
//在MVVM架构中 Repository负责从网络或本地数据库获取数据 并将数据提供给ViewModel 这样可以让ViewModel只关注数据的展示逻辑 而不用关心数据是怎么来的
//getTopNews方法：调用API接口获取新闻数据，参数和返回值都和API接口保持一致。用suspend修饰，表示要在协程中调用，避免阻塞主线程