package com.example.newsapp.hilts

import android.content.Context
import androidx.room.Room
import com.example.newsapp.common.Constants
import com.example.newsapp.data.model.dao.NewsDao
import com.example.newsapp.data.remote.NewsApiInterface
import com.example.newsapp.data.repository.NewsListRepositoryImpl
import com.example.newsapp.database.NewsAppDatabase
import com.example.newsapp.domain.repository.NewListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        NewsAppDatabase::class.java,
        "news_app"
    ).build() // The reason we can construct a database for the repo

    @Provides
    @Singleton
    fun provideNewsListRepository(newsApiInterface: NewsApiInterface): NewListRepository {
        return NewsListRepositoryImpl(newsApiInterface)
    }

    @Provides
    @Singleton
    fun provideNewsDao(db: NewsAppDatabase): NewsDao {
        return db.getNewsDao()
    }
}