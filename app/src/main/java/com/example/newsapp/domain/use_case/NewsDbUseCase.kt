package com.example.newsapp.domain.use_case

import androidx.lifecycle.LiveData
import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.domain.repository.NewsDataRepository
import javax.inject.Inject

class NewsDbUseCase @Inject constructor(private val newsDatabaseRepo: NewsDataRepository) {

    fun insertNewsData(newsBookMark: NewsBookMark) {
        newsDatabaseRepo.insertNewsData(newsBookMark)
    }

    fun deleteNewsBookMark() {
        newsDatabaseRepo.deleteNewsBookMark()
    }

    fun getNewsBookMark(ids: Int): NewsBookMark {
        return newsDatabaseRepo.getNewsBookMark(ids)
    }

    fun getCount(): LiveData<Int> {
        return newsDatabaseRepo.getCount()
    }

}