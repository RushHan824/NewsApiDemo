package com.example.news_api_demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: Repository):ViewModel(){
    private val _newsList = MutableLiveData<News_Items>()
    val newsList:LiveData<News_Items> = _newsList

    fun fetchTopNews(country:String,apikey:String){
        viewModelScope.launch{
            try{
                val result=repository.getTopNews(country,apikey)
                _newsList.value=result
            }catch(_:Exception){ }
        }
    }
}