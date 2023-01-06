package com.example.newsapp.domain.use_case

import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.domain.repository.NewsDataRepository
import javax.inject.Inject

/**
 * NewsDbUseCase class to perform news bookmark from room db
 */
class NewsDbUseCase @Inject constructor(private val newsDatabaseRepo: NewsDataRepository) {

    fun insertNewsData(newsBookMark: NewsBookMark) {
        newsDatabaseRepo.insertNewsData(newsBookMark)
    }

    fun getNewsBookMarkCount(ids: Int?): Int {
        return newsDatabaseRepo.getNewsBookMarkCount(ids)
    }

    fun getNewsBookMark(): List<NewsBookMark> {
        return newsDatabaseRepo.getNewsBookMark()
    }
}