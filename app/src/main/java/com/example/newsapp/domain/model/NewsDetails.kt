package com.example.newsapp.domain.model

import com.example.newsapp.data.model.Source

data class NewsDetails(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
