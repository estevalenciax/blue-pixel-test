package com.ev.bluepixel.jokes.data.api

import com.ev.bluepixel.core.network.RetrofitHelper
import com.ev.bluepixel.jokes.data.model.api.response.JokeResponse
import retrofit2.Retrofit

class JokeService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofitJokes()

    suspend fun getJoke(): JokeResponse {
        val response = retrofit.create(JokeClient::class.java).getJoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Error al obtener la broma")
        }
    }
}