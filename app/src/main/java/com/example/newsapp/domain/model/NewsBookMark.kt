package com.example.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "newData")
data class NewsBookMark(
    @PrimaryKey(autoGenerate = true)
    val idn: Int,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val publishedAt:String,
    val content:String
)
