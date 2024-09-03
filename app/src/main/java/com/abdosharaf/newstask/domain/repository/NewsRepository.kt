package com.abdosharaf.newstask.domain.repository

import com.abdosharaf.newstask.core.utils.ResultWrapper
import com.abdosharaf.newstask.domain.model.TopHeadlinesResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(category: String): Flow<ResultWrapper<TopHeadlinesResponse>>
}