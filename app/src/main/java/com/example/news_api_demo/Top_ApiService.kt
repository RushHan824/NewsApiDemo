package com.example.news_api_demo

import com.example.news_api_demos.News_Items
import retrofit2.http.GET
import retrofit2.http.Query

interface Top_ApiService {
    @GET("top-headlines")
    suspend fun get_topNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): News_Items
}
//我们访问一个网页需要网址 组成可以是baseurl+路径+参数
//这里@GET就是一种请求方式 表示从网页里拿数据 括号里"top-headlines"可以表示具体的路径
//suspend挂起 和kotlin中协程一起搭配 不会阻塞主线程 执行网络请求这样的耗时操作的时候 可以避免界面卡顿
//接下去两个Query就是两个参数
//最后返回类型是数据类
//返回类型通常是我们根据JSON结构定义的数据类（如News_Items），Retrofit会自动帮我们把JSON解析成这个类的对象