package com.abdosharaf.newstask.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme

@Preview
@Composable
private fun HomeScreenPreview() {
    NewsTaskTheme {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreen(
    navigateToDetails: (DomainArticle) -> Unit,
) {

}

@Composable
private fun HomeScreenContent(
) {

}