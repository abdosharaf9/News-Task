package com.abdosharaf.newstask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdosharaf.newstask.core.utils.Constants.ARTICLES_TABLE
import com.abdosharaf.newstask.domain.model.DomainArticle

@Entity(tableName = ARTICLES_TABLE)
data class ArticleEntity(
    @PrimaryKey
    val url: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val urlToImage: String,
    val category: String
) {
    fun toDomainArticle() = DomainArticle(
        url = url,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        urlToImage = urlToImage
    )

}

fun DomainArticle.toArticleEntity(category: String) = ArticleEntity(
    category = category,
    url = url,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source,
    title = title,
    urlToImage = urlToImage
)
