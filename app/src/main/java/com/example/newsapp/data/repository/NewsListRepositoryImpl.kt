package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsListDTO
import com.example.newsapp.data.remote.NewsApiInterface
import com.example.newsapp.domain.repository.NewListRepository

class NewsListRepositoryImpl(private val newsApiInterface: NewsApiInterface) : NewListRepository {
    override suspend fun getNewsList(): NewsListDTO {
        return newsApiInterface.getSearchMealList()
    }
}