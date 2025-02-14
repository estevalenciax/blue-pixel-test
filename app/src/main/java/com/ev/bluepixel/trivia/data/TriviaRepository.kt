package com.ev.bluepixel.trivia.data

import com.ev.bluepixel.core.PruebaApp
import com.ev.bluepixel.model.Question
import com.ev.bluepixel.model.api.response.toQuestions
import com.ev.bluepixel.model.room.toQuestions
import com.ev.bluepixel.model.toQuestionEntity
import com.ev.bluepixel.trivia.data.network.TriviaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TriviaRepository {
    private val service = TriviaService()
    private val database = PruebaApp.datablase.triviaDao()

    suspend fun getQuestions(): List<Question> {
        val response = service.getQuestions()
        return response?.results?.toQuestions() ?: emptyList()
    }

    suspend fun getQuestionsFromRoom(): List<Question> {
        return withContext(Dispatchers.IO) {
            val response = database.getSavedQuestions()
            response.toQuestions()
        }

    }

    suspend fun saveQuestion(question: Question) {
        withContext(Dispatchers.IO) {
            database.saveQuestion(question.toQuestionEntity())
        }
    }
}