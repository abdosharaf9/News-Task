package com.abdosharaf.newstask.domain.usecases

import com.abdosharaf.newstask.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(category: String) = newsRepository.getTopHeadlines(category)
}