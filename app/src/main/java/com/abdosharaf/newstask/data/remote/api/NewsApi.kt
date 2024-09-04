package com.abdosharaf.newstask.data.remote.api

import com.abdosharaf.newstask.data.remote.dto.TopHeadlinesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("top-headlines/category/{category}/us.json")
    suspend fun getTopHeadlines(
        @Path("category") category: String
    ): TopHeadlinesDto
}