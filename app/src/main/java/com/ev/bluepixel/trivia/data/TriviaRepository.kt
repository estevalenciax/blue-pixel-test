package com.ev.bluepixel.trivia.data

import com.ev.bluepixel.model.Question
import com.ev.bluepixel.model.api.response.toQuestion
import com.ev.bluepixel.trivia.data.network.TriviaService

class TriviaRepository {
    private val service = TriviaService()

    suspend fun getQuestion(): Question {
        val response = service.getQuestions()
        return response[0].toQuestion()
    }
}