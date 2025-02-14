package com.ev.bluepixel.jokes.data

import com.ev.bluepixel.jokes.data.model.Joke
import com.ev.bluepixel.jokes.data.api.JokeService
import com.ev.bluepixel.jokes.data.model.api.response.toJoke

class JokeRepository {
    val service = JokeService()

    suspend fun getJoke() : Joke {
        try {
            val response = service.getJoke()
            return response.toJoke()
        }catch (e: Exception){
            throw Exception(e.message)
        }
    }
}