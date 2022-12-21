package com.example.newsapp.domain.use_case

import android.util.Log
import com.example.newsapp.common.Resource
import com.example.newsapp.data.model.NewsListDTO
import com.example.newsapp.data.model.toDomainNews
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewsSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsSearchUseCase @Inject constructor(private val newsSearchRepository: NewsSearchRepository) {
    lateinit var data: NewsListDTO
    operator fun invoke(query: String): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())
            data = newsSearchRepository.getSearchDataList(query)
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