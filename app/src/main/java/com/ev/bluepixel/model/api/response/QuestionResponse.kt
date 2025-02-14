package com.ev.bluepixel.model.api.response

import com.ev.bluepixel.model.Question

data class QuestionResponse(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)

fun QuestionResponse.toQuestion(): Question {
    return Question(
        category = this.category,
        correct_answer = this.correct_answer,
        difficulty = this.difficulty,
        incorrect_answers = this.incorrect_answers,
        question = this.question,
        type = this.type
    )
}