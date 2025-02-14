package com.ev.bluepixel.trivia.data.model.api.response

data class ApiResponse(
    val response_code: Int,
    val results: List<QuestionResponse>
)