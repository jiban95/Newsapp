package com.example.newsapp.presentation.news_db_operation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.domain.use_case.NewsDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsDatabaseViewModel @Inject constructor(private val newsDbUseCase: NewsDbUseCase) :
    ViewModel() {
    fun insertNewsData(newsBookMark: NewsBookMark) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDbUseCase.insertNewsData(newsBookMark)
        }
    }

    fun deleteNewsBookMark() {
        viewModelScope.launch(Dispatchers.IO) {
            newsDbUseCase.deleteNewsBookMark()
        }
    }

    suspend fun getNewsBookMark(): List<NewsBookMark> = withContext(Dispatchers.IO) {
        return@withContext newsDbUseCase.getNewsBookMark()
    }
}