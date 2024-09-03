package com.abdosharaf.newstask.presentation.navigation

import com.abdosharaf.newstask.domain.model.DomainArticle
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailsRoute(
    val article: DomainArticle
)