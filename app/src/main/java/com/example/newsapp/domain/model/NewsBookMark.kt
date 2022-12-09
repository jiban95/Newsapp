package com.example.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "newData")
data class NewsBookMark(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title:String,
    var description:String,
    var url:String,
    var urlToImage:String,
    var publishedAt:String,
    var content:String
) {
    constructor() : this(0,"","","","","","")
}
