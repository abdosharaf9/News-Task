package com.abdosharaf.newstask.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme

@Preview
@Composable
private fun DetailsScreenPreview() {
    NewsTaskTheme {
        DetailsScreenContent()
    }
}

@Composable
fun DetailsScreen(
    article: DomainArticle,
    navigateBack: () -> Unit
) {
    DetailsScreenContent(

    )
}

@Composable
private fun DetailsScreenContent(

) {

}