package com.ev.bluepixel.jokes.data.network

import com.ev.bluepixel.core.network.RetrofitHelper
import com.ev.bluepixel.jokes.data.model.api.response.JokeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class JokeService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofitJokes()

    suspend fun getJoke(): JokeResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(JokeClient::class.java).getJoke()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception("Error al obtener la broma")
            }
        }
    }

}