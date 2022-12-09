package com.example.newsapp.domain.use_case

import android.util.Log
import com.example.newsapp.common.Resource
import com.example.newsapp.data.model.toDomainNews
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsListUseCase @Inject constructor(private val newListRepository: NewListRepository) {

    operator fun invoke(): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())

            val data = newListRepository.getNewsList()
            val domain = if (data.articles != null) data.articles.map { it.toDomainNews() } else emptyList()

            emit(Resource.Success(data = domain))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }
}