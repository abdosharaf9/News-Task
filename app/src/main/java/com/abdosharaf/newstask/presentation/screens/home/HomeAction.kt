package com.abdosharaf.newstask.presentation.screens.home

import com.abdosharaf.newstask.presentation.screens.home.components.Category

sealed class HomeAction {
    data class OnCategoryClicked(val category: Category) : HomeAction()
}
