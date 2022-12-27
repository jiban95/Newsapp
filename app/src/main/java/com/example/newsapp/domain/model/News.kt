package com.example.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val publishedAt: String,
    val urlToImage: String,
    val description: String,
    val url: String,
    val content: String
) : Parcelable
