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

    suspend fun getJokev2(): Result<JokeResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(JokeClient::class.java).getJokev2()
                Result.Success(response)
            } catch (e: IOException) {
                Result.Error(NetworkException("Error de conexión"))
            } catch (e: retrofit2.HttpException) {
                Result.Error(ServerException("Error en el servidor: ${e.code()}"))
            } catch (e: Exception) {
                Result.Error(UnknownException("Error desconocido: ${e.message}"))
            }
        }
    }

}