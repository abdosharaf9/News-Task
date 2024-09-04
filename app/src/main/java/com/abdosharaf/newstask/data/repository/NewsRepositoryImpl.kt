package com.abdosharaf.newstask.data.repository

import com.abdosharaf.newstask.core.utils.ResultWrapper
import com.abdosharaf.newstask.data.local.ArticlesDao
import com.abdosharaf.newstask.data.local.toArticleEntity
import com.abdosharaf.newstask.data.remote.api.NewsApi
import com.abdosharaf.newstask.domain.model.TopHeadlinesResponse
import com.abdosharaf.newstask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articlesDao: ArticlesDao
) : NewsRepository {

    override suspend fun getTopHeadlines(category: String) = flow {
        emit(ResultWrapper.Loading())

        val articles = articlesDao.getArticles(category = category)
        val localResponse = TopHeadlinesResponse(
            articles = articles.map { it.toDomainArticle() },
            totalResults = articles.size
        )
        emit(ResultWrapper.Loading(data = localResponse))

        try {
            val serverResponse =
                newsApi.getTopHeadlines(category = category).toTopHeadlinesResponse()
            articlesDao.deleteArticles(category = category)
            articlesDao.insertArticles(
                articles = serverResponse.articles.map { it.toArticleEntity(category = category) }
            )
        } catch (e: IOException) {
            emit(ResultWrapper.NoInternet(data = localResponse))
        } catch (t: Throwable) {
            t.printStackTrace()
            emit(ResultWrapper.Failure(data = localResponse))
        }

        val newArticles = articlesDao.getArticles(category = category)
        emit(
            ResultWrapper.Success(
                TopHeadlinesResponse(
                    articles = newArticles.map { it.toDomainArticle() },
                    totalResults = newArticles.size
                )
            )
        )
    }
}