package com.ev.bluepixel.jokes.data

import com.ev.bluepixel.core.PruebaApp
import com.ev.bluepixel.data.Result
import com.ev.bluepixel.jokes.data.model.Joke
import com.ev.bluepixel.jokes.data.network.JokeService
import com.ev.bluepixel.jokes.data.model.api.response.toJoke
import com.ev.bluepixel.jokes.data.model.room.toJokes
import com.ev.bluepixel.jokes.data.model.toJokeEntity
import com.ev.bluepixel.jokes.data.room.JokeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokeRepository @Inject constructor(private val service : JokeService, private val database : JokeDao) {

    suspend fun getJoke() : Result<Joke> {
        val response = service.getJoke()
        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.toJoke())
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
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