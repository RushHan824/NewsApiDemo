package com.example.news_api_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_api_demo.Article
import com.example.news_api_demo.R

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var articles: List<Article> = emptyList()

    fun submitList(list: List<Article>) {//提交
        articles = list
        notifyDataSetChanged()//通知recyclerview 数据发生变化 刷新一下
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }//复制粘贴

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }//当前位置数据绑定

    override fun getItemCount(): Int = articles.size//告诉recyclerview有多少条数据

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {//
        private val titleTextView: TextView = itemView.findViewById(R.id.newsTitle)
        private val imageView: ImageView = itemView.findViewById(R.id.newsImage)

        fun bind(article: Article) {//将article数据绑定到控件上
            titleTextView.text = article.title
            Glide.with(itemView.context).load(article.urlToImage).into(imageView)//Glide高效加载网络图片，防止界面卡顿
        }
    }
}

//NewsAdapter：是 RecyclerView 的“桥梁”，负责把新闻数据展示到每一行 item 上
//onCreateViewHolder：每当需要一个新的 item 行时，加载 item_news.xml 布局，生成 ViewHolder
//onBindViewHolder：把对应位置的数据绑定到 ViewHolder 上
//NewsViewHolder：用来缓存和管理 item_news.xml 里的控件，避免重复查找