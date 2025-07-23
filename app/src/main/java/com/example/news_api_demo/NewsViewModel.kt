package com.example.news_api_demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_api_demos.News_Items
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: Repository):ViewModel(){
    private val _newsList = MutableLiveData<News_Items>()//不展示给ui
    val newsList:LiveData<News_Items> = _newsList//展示给ui

    fun fetchTopNews(country:String,apikey:String){//调用函数
        viewModelScope.launch{
            try{
                val result=repository.getTopNews(country,apikey)//调用函数 返回值给到result
                _newsList.value=result
            }catch(_:Exception){ }
        }
    }
}
//ViewModel：用于管理界面数据，保证数据在界面旋转等情况下不会丢失。
//      比如你在看新闻列表，突然手机横屏了，Activity会被销毁再重建。
//      如果你把数据直接写在Activity里，界面重建后，数据会丢失，需要重新请求。
//ViewModel的作用就是：即使界面重建，ViewModel里的数据还在，不会丢失，这样用户体验更好。

//LiveData/MutableLiveData：实现数据的观察者模式，界面可以自动感知数据变化并刷新。
//viewModelScope.launch：在ViewModel自带的协程作用域中启动协程，进行异步操作（如网络请求），不会阻塞主线程。
//异常捕获：防止网络请求失败导致程序崩溃，可以在catch里加上错误提示