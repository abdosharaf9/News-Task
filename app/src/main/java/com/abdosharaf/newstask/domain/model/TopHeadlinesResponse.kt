package com.abdosharaf.newstask.domain.model

data class TopHeadlinesResponse(
    val articles: List<DomainArticle>,
    val totalResults: Int
)
