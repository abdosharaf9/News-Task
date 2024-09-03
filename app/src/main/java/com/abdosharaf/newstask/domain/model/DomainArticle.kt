package com.abdosharaf.newstask.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DomainArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
