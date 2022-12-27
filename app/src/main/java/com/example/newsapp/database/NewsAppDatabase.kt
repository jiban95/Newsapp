package com.example.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.dao.NewsDao
import com.example.newsapp.domain.model.NewsBookMark

/**
 * DataBase class for NewsBookmark
 */
@Database(entities = [NewsBookMark::class], version = 1, exportSchema = false)
abstract class NewsAppDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}