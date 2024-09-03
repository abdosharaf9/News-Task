package com.abdosharaf.newstask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.screens.details.DetailsScreen
import com.abdosharaf.newstask.presentation.screens.home.HomeScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        composable<HomeRoute> {
            HomeScreen(
                navigateToDetails = { article ->
                    navController.navigate(DetailsRoute(article = article))
                }
            )
        }

        composable<DetailsRoute>(
            typeMap = mapOf(
                typeOf<DomainArticle>() to CustomNavType.ArticleType
            )
        ) {
            val args = it.toRoute<DetailsRoute>()

            DetailsScreen(
                article = args.article,
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}