package com.abdosharaf.newstask.presentation.screens.home.components

data class Category(
    val label: String,
    val id: String
)

val categoriesList = listOf(
    Category(label = "Business", id = "business"),
    Category(label = "Entertainment", id = "entertainment"),
    Category(label = "General", id = "general"),
    Category(label = "Health", id = "health"),
    Category(label = "Science", id = "science"),
    Category(label = "Sports", id = "sports"),
    Category(label = "Technology", id = "technology")
)
