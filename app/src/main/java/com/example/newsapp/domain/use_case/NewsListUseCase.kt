package com.example.newsapp.domain.use_case

import android.util.Log
import com.example.newsapp.common.Resource
import com.example.newsapp.data.model.NewsListDTO
import com.example.newsapp.data.model.toDomainNews
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * NewsListUseCase class to get  news, events and weather data from api
 */
class NewsListUseCase @Inject constructor(private val newListRepository: NewListRepository) {
    lateinit var data: NewsListDTO
    operator fun invoke(newsType: Int): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())
            when (newsType) {
                1 -> {
                    data = newListRepository.getNewsList()
                }
                2 -> {
                    data = newListRepository.getEventList()
                }
                3 -> {
                    data = newListRepository.getWeatherList()
                }
            }
            val domain = data.articles.map { it.toDomainNews() }
            emit(Resource.Success(data = domain))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("error", it) }
        }
    }
}