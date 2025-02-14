package com.ev.bluepixel.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ev.bluepixel.model.Question

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String = "",
    val correct_answer: String = "",
    val difficulty: String = "",
//    val incorrect_answers: List<String> = emptyList(),
    val question: String = "",
    val type: String = "",
)

fun List<QuestionEntity>.toQuestions(): List<Question> {
    return this.map { entity ->
        Question(
            category = entity.category,
            correct_answer = entity.correct_answer,
            difficulty = entity.difficulty,
            question = entity.question,
            type = entity.type
        )
    }
}