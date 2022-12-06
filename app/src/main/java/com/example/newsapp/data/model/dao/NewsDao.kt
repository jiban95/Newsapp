package com.example.newsapp.data.model.dao

import androidx.room.*
import com.example.newsapp.domain.model.NewsBookMark

@Dao
interface NewsDao {
    @Insert
    fun insert(table: NewsBookMark)

    @Update
    fun update(table: NewsBookMark?)

    @Delete
    fun delete(table: NewsBookMark?)

}