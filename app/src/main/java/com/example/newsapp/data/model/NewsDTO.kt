package com.example.newsapp.data.model

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsDetails

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
        urlToImage = this.urlToImage ?: ""
    )
}
fun NewsDTO.toDomainNewsDetails():NewsDetails{
    return NewsDetails(
        author=this.author?:"",
        content=this.content?:"",
        description = this.description?:"",
        publishedAt = this.publishedAt?:"",
        title = this.title?:"",
        url= this.url?:"",
        urlToImage= this.urlToImage?:"")
}