package com.abdosharaf.newstask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdosharaf.newstask.core.utils.Constants.ARTICLES_TABLE

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM $ARTICLES_TABLE WHERE category = :category")
    suspend fun deleteArticles(category: String)

    @Query("SELECT * FROM $ARTICLES_TABLE WHERE category = :category")
    suspend fun getArticles(category: String): List<ArticleEntity>
}