package com.ev.bluepixel.jokes.data.network

import com.ev.bluepixel.jokes.data.model.api.response.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokeClient {

    @GET("/jokes/random")
    suspend fun getJoke() : Response<JokeResponse>
}