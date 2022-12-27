package com.example.newsapp.presentation.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.Resource
import com.example.newsapp.domain.use_case.NewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val newsListUseCase: NewsListUseCase) :
    ViewModel() {

    private val _newsList = MutableStateFlow(NewsListState())
    val newsList: StateFlow<NewsListState> = _newsList

    /**
     * method to get all news from news api
     */
    fun getNewsData(newsType: Int) {
        newsListUseCase(newsType).onEach {
            when (it) {
                is Resource.Loading -> {
                    _newsList.value = NewsListState(isLoading = true)
                }
                is Resource.Success -> {
                    _newsList.value = NewsListState(data = it.data)
                }
                is Resource.Error -> {
                    _newsList.value = NewsListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * method to get all events data from events api
     */
    fun getEventData(newsType: Int) {
        newsListUseCase(newsType).onEach {
            when (it) {
                is Resource.Loading -> {
                    _newsList.value = NewsListState(isLoading = true)
                }
                is Resource.Success -> {
                    _newsList.value = NewsListState(data = it.data)
                }
                is Resource.Error -> {
                    _newsList.value = NewsListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * method to get all weather from weather api
     */
    fun getWeatherData(newsType: Int) {
        newsListUseCase(newsType).onEach {
            when (it) {
                is Resource.Loading -> {
                    _newsList.value = NewsListState(isLoading = true)
                }
                is Resource.Success -> {
                    _newsList.value = NewsListState(data = it.data)
                }
                is Resource.Error -> {
                    _newsList.value = NewsListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}