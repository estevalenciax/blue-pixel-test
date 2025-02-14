package com.ev.bluepixel.trivia.data.network

import com.ev.bluepixel.core.network.RetrofitHelper
import com.ev.bluepixel.model.api.response.QuestionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TriviaService() {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuestions(): List<QuestionResponse> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TriviaClient::class.java).getQuestions()
            if (response.body() != null) {
                if (response.body()!!.response_code == 0) {
                    response.body()!!.results
                } else {
                    emptyList()
                }
            } else{
                emptyList()
            }
        }

    }

}