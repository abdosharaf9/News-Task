package com.abdosharaf.newstask.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdosharaf.newstask.R
import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.screens.home.components.ArticleItem
import com.abdosharaf.newstask.presentation.screens.home.components.Category
import com.abdosharaf.newstask.presentation.screens.home.components.CategoryItem
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme

@Preview
@Composable
private fun HomeScreenPreview() {
    NewsTaskTheme {
        HomeScreenContent(
            uiState = HomeUIState(
                articles = (1..10).map {
                    DomainArticle(
                        author = "",
                        content = "",
                        description = "",
                        publishedAt = "2022-04-21T09:53:00Z",
                        source = "BBC",
                        title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        url = "$it",
                        urlToImage = ""
                    )
                },
                resultCount = 10
            )
        )
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetails: (DomainArticle) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreenContent(
        uiState = uiState,
        navigateToDetails = navigateToDetails,
        handleActions = viewModel::handleActions
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUIState = HomeUIState(),
    navigateToDetails: (DomainArticle) -> Unit = {},
    handleActions: (HomeAction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoriesSection(
            uiState.categories,
            uiState.selectedCategory,
            onCategoryClicked = {
                handleActions(HomeAction.OnCategoryClicked(it))
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        ArticlesSection(
            isLoading = uiState.isLoading,
            articles = uiState.articles,
            resultCount = uiState.resultCount,
            onArticleClicked = navigateToDetails
        )
    }
}

@Composable
private fun CategoriesSection(
    categories: List<Category>,
    selectedCategory: Category,
    onCategoryClicked: (Category) -> Unit
) {
    Text(
        text = stringResource(id = R.string.categories),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(4.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items = categories, key = { it.id }) { category ->
            CategoryItem(
                category = category.label,
                isSelected = selectedCategory == category,
                onClick = { onCategoryClicked(category) }
            )
        }
    }
}

@Composable
private fun ArticlesSection(
    isLoading: Boolean,
    articles: List<DomainArticle>,
    resultCount: Int,
    onArticleClicked: (DomainArticle) -> Unit
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    AnimatedVisibility(
        visible = !isLoading && resultCount == 0,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // TODO: Empty state!!
    }

    AnimatedVisibility(
        visible = !isLoading && resultCount != 0,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.results, resultCount),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                items(items = articles, key = { it.url }) { article ->
                    ArticleItem(
                        article = article,
                        onClick = { onArticleClicked(article) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}