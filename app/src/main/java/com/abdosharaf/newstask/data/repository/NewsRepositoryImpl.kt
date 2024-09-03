package com.abdosharaf.newstask.data.repository

import com.abdosharaf.newstask.core.utils.safeCall
import com.abdosharaf.newstask.data.api.NewsApi
import com.abdosharaf.newstask.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getTopHeadlines(category: String) = safeCall {
        newsApi.getTopHeadlines(category).toTopHeadlinesResponse()
    }
}