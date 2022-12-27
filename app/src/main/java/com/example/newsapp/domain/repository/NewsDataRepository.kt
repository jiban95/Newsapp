package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.dao.NewsDao
import com.example.newsapp.domain.model.NewsBookMark
import javax.inject.Inject

class NewsDataRepository @Inject constructor(private val newsDao: NewsDao) {

    fun insertNewsData(newsBookMark: NewsBookMark) {
        newsDao.insert(newsBookMark)
    }

    fun deleteNewsBookMark() {
        newsDao.deleteAllNews()
    }

    fun getNewsBookMark(): List<NewsBookMark> {
        return newsDao.getNewsBookMark()
    }
}