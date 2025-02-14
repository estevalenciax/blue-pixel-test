package com.ev.bluepixel.trivia.data

import com.ev.bluepixel.model.Question
import com.ev.bluepixel.model.api.response.toQuestions
import com.ev.bluepixel.trivia.data.network.TriviaService

class TriviaRepository {
    private val service = TriviaService()

    suspend fun getQuestions(): List<Question> {
        val response = service.getQuestions()
        return response?.results?.toQuestions() ?: emptyList()
    }
}