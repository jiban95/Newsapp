package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.NewsListDTO

interface NewListRepository {
    suspend fun getNewsList(): NewsListDTO
}