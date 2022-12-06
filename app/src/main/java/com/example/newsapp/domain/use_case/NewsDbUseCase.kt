package com.example.newsapp.domain.use_case

import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.domain.repository.NewsDataRepository
import javax.inject.Inject

class NewsDbUseCase @Inject constructor(private val newsDatabaseRepo: NewsDataRepository) {

    suspend fun insertNewsData(newsBookMark: NewsBookMark) {
        newsDatabaseRepo.insertNewsData(newsBookMark)
    }

}