package com.abdosharaf.newstask.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
        enterTransition = {
            fadeIn(animationSpec = tween())
        },
        exitTransition = {
            fadeOut(animationSpec = tween())
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween())
        },
        popExitTransition = {
            fadeOut(animationSpec = tween())
        },
        modifier = modifier
    ) {
        composable<HomeRoute> {
            HomeScreen(
                navigateToDetails = { article, category ->
                    navController.navigate(DetailsRoute(article = article, category = category))
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
                category = args.category,
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}