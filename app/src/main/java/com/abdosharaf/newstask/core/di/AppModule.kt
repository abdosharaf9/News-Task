package com.abdosharaf.newstask.core.di

import android.content.Context
import androidx.room.Room
import com.abdosharaf.newstask.core.utils.Constants.BASE_URL
import com.abdosharaf.newstask.core.utils.Constants.DATABASE_NAME
import com.abdosharaf.newstask.core.utils.Constants.TAG
import com.abdosharaf.newstask.data.local.NewsDatabase
import com.abdosharaf.newstask.data.remote.api.NewsApi
import com.abdosharaf.newstask.data.repository.NewsRepositoryImpl
import com.abdosharaf.newstask.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor {
            Timber.tag(TAG).e(it)
        }

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            callTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NewsDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsDatabase: NewsDatabase): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi, articlesDao = newsDatabase.articlesDao)
    }
}