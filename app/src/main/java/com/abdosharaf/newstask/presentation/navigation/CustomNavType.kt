package com.abdosharaf.newstask.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.abdosharaf.newstask.domain.model.DomainArticle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val ArticleType = object : NavType<DomainArticle>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): DomainArticle? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): DomainArticle {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: DomainArticle): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: DomainArticle) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}