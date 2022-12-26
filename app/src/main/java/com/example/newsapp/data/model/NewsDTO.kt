package com.example.newsapp.data.model

import com.example.newsapp.domain.model.News

data class NewsDTO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun NewsDTO.toDomainNews(): News {
    return News(
        title = this.title ?: "",
        publishedAt = this.publishedAt ?: "",
        urlToImage = this.urlToImage ?: "",
        description = this.description ?: "",
        url = this.url ?: "",
        content = this.content ?: ""
    )
}
