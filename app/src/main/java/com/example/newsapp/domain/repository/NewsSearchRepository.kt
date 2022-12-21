package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.NewsListDTO

interface NewsSearchRepository {
    suspend fun getSearchDataList(query: String): NewsListDTO
}