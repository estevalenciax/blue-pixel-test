package com.ev.bluepixel.model

data class Question(
    val category: String = "",
    val correct_answer: String = "",
    val difficulty: String = "",
    val incorrect_answers: List<String> = emptyList(),
    val question: String = "",
    val type: String = ""
)


