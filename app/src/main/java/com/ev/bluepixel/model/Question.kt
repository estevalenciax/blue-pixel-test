package com.ev.bluepixel.model

import com.ev.bluepixel.model.room.QuestionEntity

data class Question(
    val category: String = "",
    val correct_answer: String = "",
    val difficulty: String = "",
    val incorrect_answers: List<String> = emptyList(),
    val question: String = "",
    val type: String = "",
    var answers: List<String> = emptyList()
)

fun Question.toQuestionEntity(): QuestionEntity {
    return QuestionEntity(
        category = this.category,
        correct_answer = this.correct_answer,
        difficulty = this.difficulty,
        question = this.question,
        type = this.type
    )
}
