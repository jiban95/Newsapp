package com.example.newsapp.data.repository

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.NewsListDTO
import com.example.newsapp.data.remote.NewsApiInterface
import com.example.newsapp.domain.repository.NewsSearchRepository

class NewsSearchListRepositoryImpl(private val newsApiInterface: NewsApiInterface) : NewsSearchRepository {

    override suspend fun getSearchDataList(query: String): NewsListDTO {
        return newsApiInterface.getSearchDataList(query, BuildConfig.API_KEY)
    }
}