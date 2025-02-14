package com.ev.bluepixel.trivia.data.network

import com.ev.bluepixel.core.network.RetrofitHelper
import com.ev.bluepixel.trivia.data.Result
import com.ev.bluepixel.trivia.data.ServerException
import com.ev.bluepixel.trivia.data.UnknownException
import com.ev.bluepixel.trivia.data.model.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class TriviaService() {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuestions(): ApiResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TriviaClient::class.java).getQuestions()
            if (response.body() != null && response.body()!!.response_code == 0) {
                response.body()!!
            } else{
                null
            }
        }

    }

    suspend fun getQuestionsv2(): Result<ApiResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(TriviaClient::class.java).getQuestionsv2()
                Result.Success(response)
            } catch (e: IOException) {
                Result.Error(com.ev.bluepixel.trivia.data.NetworkException("Error de conexión"))
            } catch (e: retrofit2.HttpException) {
                Result.Error(ServerException("Error en el servidor: ${e.code()}"))
            } catch (e: Exception) {
                Result.Error(UnknownException("Error desconocido: ${e.message}"))
            }

        }

    }

}