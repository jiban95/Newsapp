package com.example.newsapp.data.model.dao

import androidx.lifecycle.LiveData
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

    @Query("SELECT count(*) FROM newData")
    fun getCount(): LiveData<Int>

    @Query("DELETE FROM newData")
    fun deleteAllNews()

    @Query("SELECT * FROM newData")
    fun getNewsBookMark():List<NewsBookMark>

}