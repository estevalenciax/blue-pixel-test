package com.ev.bluepixel.trivia.data.network

import com.ev.bluepixel.trivia.data.model.api.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaClient {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int,
        @Query("type") type: String
    ): ApiResponse
}