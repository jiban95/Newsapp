package com.example.newsapp.data.remote

import com.example.newsapp.data.model.NewsListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

        @GET("top-headlines?sources=google-news-in&apiKey=d30ee69da98c4d328e5100826602fa50")
        suspend fun getSearchMealList(): NewsListDTO
}