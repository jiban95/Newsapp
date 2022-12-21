package com.example.newsapp.data.remote


import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.NewsListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

    @GET("top-headlines?sources=google-news-in&apiKey=${BuildConfig.API_KEY}")
    suspend fun getNewsList(): NewsListDTO

    @GET("top-headlines?q=event&apiKey=${BuildConfig.API_KEY}")
    suspend fun getEventList(): NewsListDTO

    @GET("top-headlines?q=weather&apiKey=${BuildConfig.API_KEY}")
    suspend fun getWeatherList(): NewsListDTO

    @GET("top-headlines")
    suspend fun getSearchDataList(@Query("q")q: String,@Query("apiKey")apiKey:String): NewsListDTO


}