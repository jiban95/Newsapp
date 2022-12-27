package com.example.newsapp.data.model

data class NewsListDTO(
    val articles: List<NewsDTO>,
    val status: String,
    val totalResults: Int,
)
