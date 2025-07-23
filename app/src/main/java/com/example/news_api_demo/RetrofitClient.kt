package com.example.news_api_demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Interceptor

object RetrofitClient {//object关键字声明了一个单例对象 这样RetrofitClient在全局只有一个实例 方便全局调用 不用每次都新建
    private val client = OkHttpClient.Builder()//OkHttpClient 是Retrofit底层用来发网络请求的库
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "Mozilla/5.0 (Android)")
                .build()
            chain.proceed(request)//表示继续执行请求
        })//这里用Builder()自定义了一个OkHttpClient 加了一个拦截器Interceptor
        //拦截器可以在请求发出前、响应返回后做一些统一处理
        //这里的拦截器给每个请求都加上了一个User-Agent请求头（模拟浏览器或App身份，有些API会校验这个） 对于现在用的api 不加这个拦截器不行
        .build()

    val api: Top_ApiService by lazy {//懒加载的属性 只有第一次用到时才会初始化 节省资源
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")//设置api基础网址
            .addConverterFactory(GsonConverterFactory.create())//用gson把json自动转换成kotlin对象
            .client(client)
            .build()//创建retrofit对象
            .create(Top_ApiService::class.java)//创建API接口的实现对象
    }
}