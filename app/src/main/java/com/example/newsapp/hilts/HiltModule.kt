package com.example.newsapp.hilts

import com.example.newsapp.common.Constants
import com.example.newsapp.common.Constants.BASE_URL
import com.example.newsapp.data.remote.NewsApiInterface
import com.example.newsapp.data.repository.NewsListRepositoryImpl
import com.example.newsapp.domain.repository.NewListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    @Singleton
    fun provideNewsAPI(): NewsApiInterface {

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsListRepository(newsApiInterface: NewsApiInterface): NewListRepository {
       return NewsListRepositoryImpl(newsApiInterface)
    }

}