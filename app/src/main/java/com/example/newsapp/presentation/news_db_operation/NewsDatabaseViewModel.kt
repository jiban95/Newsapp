package com.example.newsapp.presentation.news_db_operation

import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.domain.use_case.NewsDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDatabaseViewModel @Inject constructor(private val newsDbUseCase: NewsDbUseCase) :
    ViewModel() {
    suspend fun insertNewsData(newsBookMark: NewsBookMark) {
        newsDbUseCase.insertNewsData(newsBookMark)
    }
}