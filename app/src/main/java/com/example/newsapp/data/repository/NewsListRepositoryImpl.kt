package com.example.newsapp.data.repository

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.NewsListDTO
import com.example.newsapp.data.remote.NewsApiInterface
import com.example.newsapp.domain.repository.NewListRepository
import com.example.newsapp.domain.repository.NewsSearchRepository

class NewsListRepositoryImpl(private val newsApiInterface: NewsApiInterface) : NewListRepository {
    override suspend fun getNewsList(): NewsListDTO {
        return newsApiInterface.getNewsList()
    }

    override suspend fun getEventList(): NewsListDTO {
        return newsApiInterface.getEventList()
    }

    override suspend fun getWeatherList(): NewsListDTO {
        return newsApiInterface.getWeatherList()
    }

}