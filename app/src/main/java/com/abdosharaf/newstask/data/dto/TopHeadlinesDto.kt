package com.abdosharaf.newstask.data.dto

import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.domain.model.TopHeadlinesResponse

data class TopHeadlinesDto(
    val articles: List<Article>?,
    val status: String,
    val totalResults: Int?
) {
    fun toTopHeadlinesResponse() = TopHeadlinesResponse(
        articles = articles?.map { it.toDomainArticle() } ?: emptyList(),
        totalResults = totalResults ?: 0
    )
}

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) {
    fun toDomainArticle() = DomainArticle(
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt ?: "",
        source = source?.name ?: "",
        title = title ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: ""
    )
}

data class Source(
    val id: String,
    val name: String?
)
