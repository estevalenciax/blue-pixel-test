package com.ev.bluepixel.trivia.data.network

import com.ev.bluepixel.core.network.RetrofitHelper
import com.ev.bluepixel.data.NetworkException
import com.ev.bluepixel.data.Result
import com.ev.bluepixel.data.ServerException
import com.ev.bluepixel.data.UnknownException
import com.ev.bluepixel.trivia.data.model.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class TriviaService() {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuestions(): Result<ApiResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(TriviaClient::class.java).getQuestions()
                Result.Success(response)
            } catch (e: IOException) {
                Result.Error(NetworkException("Verifica tu conexión a internet"))
            } catch (e: retrofit2.HttpException) {
                if (e.code() == 429) {
                    Result.Error(ServerException("Espera un momento para una nueva pregunta."))
                } else {
                    Result.Error(ServerException("Error en el servidor: ${e.message()}"))
                }
            } catch (e: Exception) {
                Result.Error(UnknownException("Error desconocido: ${e.message}"))
            }

        }
    }

}