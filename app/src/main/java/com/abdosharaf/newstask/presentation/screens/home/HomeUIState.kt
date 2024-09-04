package com.abdosharaf.newstask.presentation.screens.home

import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.screens.home.components.Category
import com.abdosharaf.newstask.presentation.screens.home.components.categoriesList

data class HomeUIState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isNetworkError: Boolean = false,
    val categories: List<Category> = categoriesList,
    val selectedCategory: Category = categoriesList.first(),
    val articles: List<DomainArticle> = emptyList(),
    val resultCount: Int = 0
)
