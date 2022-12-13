package com.example.newsapp.data.remote


import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.NewsListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

        @GET("top-headlines?sources=google-news-in&apiKey=${BuildConfig.API_KEY}")
        suspend fun getNewsList(): NewsListDTO

}