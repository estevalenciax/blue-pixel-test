package com.ev.bluepixel.jokes.data

import com.ev.bluepixel.core.PruebaApp
import com.ev.bluepixel.jokes.data.model.Joke
import com.ev.bluepixel.jokes.data.network.JokeService
import com.ev.bluepixel.jokes.data.model.api.response.toJoke
import com.ev.bluepixel.jokes.data.model.room.toJokes
import com.ev.bluepixel.jokes.data.model.toJokeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeRepository {
    val service = JokeService()
    val database = PruebaApp.datablase.jokeDao()

    suspend fun getJoke() : Joke {
        try {
            val response = service.getJoke()
            return response.toJoke()
        }catch (e: Exception){
            throw Exception(e.message)
        }
    }

    suspend fun getJokesFromRoom(): List<Joke> {
        return withContext(Dispatchers.IO) {
            val response = database.getJokes()
            response.toJokes()
        }
    }

    suspend fun saveJoke(joke: Joke) {
        withContext(Dispatchers.IO) {
            database.saveJoke(joke.toJokeEntity())
        }
    }
}