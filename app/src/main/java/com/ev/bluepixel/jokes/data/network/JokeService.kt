package com.ev.bluepixel.jokes.data.network

import com.ev.bluepixel.core.network.RetrofitHelper
import com.ev.bluepixel.data.NetworkException
import com.ev.bluepixel.data.Result
import com.ev.bluepixel.data.ServerException
import com.ev.bluepixel.data.UnknownException
import com.ev.bluepixel.jokes.data.model.api.response.JokeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

class JokeService @Inject constructor(private val jokeClient: JokeClient) {


    suspend fun getJoke(): Result<JokeResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = jokeClient.getJoke()
                Result.Success(response)
            } catch (e: IOException) {
                Result.Error(NetworkException("Creo que Chuck Norris te cortó el internet :("))
            } catch (e: retrofit2.HttpException) {
                Result.Error(ServerException("Error en el servidor: ${e.code()}"))
            } catch (e: Exception) {
                Result.Error(UnknownException("Error desconocido: ${e.message}"))
            }
        }
    }

}