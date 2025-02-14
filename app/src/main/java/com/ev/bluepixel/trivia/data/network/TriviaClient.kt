package com.ev.bluepixel.trivia.data.network

import com.ev.bluepixel.trivia.data.model.api.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface TriviaClient {

    @GET("/api.php?amount=1&type=multiple")
    suspend fun getQuestions(): Response<ApiResponse>

    @GET("/api.php?amount=1&type=multiple")
    suspend fun getQuestionsv2(): ApiResponse
}