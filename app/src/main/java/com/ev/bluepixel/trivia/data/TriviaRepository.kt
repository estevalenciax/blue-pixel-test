package com.ev.bluepixel.trivia.data

import com.ev.bluepixel.core.PruebaApp
import com.ev.bluepixel.data.Result
import com.ev.bluepixel.trivia.data.model.Question
import com.ev.bluepixel.trivia.data.model.api.response.toQuestions
import com.ev.bluepixel.trivia.data.model.room.toQuestions
import com.ev.bluepixel.trivia.data.model.toQuestionEntity
import com.ev.bluepixel.trivia.data.network.TriviaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TriviaRepository {
    private val service = TriviaService()
    private val database = PruebaApp.datablase.triviaDao()

    suspend fun getQuestions(): Result<List<Question>> {
        val response = service.getQuestions()
        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.results.toQuestions())
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
        }
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