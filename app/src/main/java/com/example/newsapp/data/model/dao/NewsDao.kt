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

    @Query("DELETE FROM newData")
    fun deleteAllNews()

    @Query("SELECT COUNT(*) FROM newData WHERE id=:ids")
    fun getNewsBookMarkCount(ids: Int?): Int

    @Query("SELECT * FROM newData")
    fun getNewsBookMark(): List<NewsBookMark>

}