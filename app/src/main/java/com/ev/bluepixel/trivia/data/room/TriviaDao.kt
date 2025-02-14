package com.ev.bluepixel.trivia.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ev.bluepixel.trivia.data.model.room.QuestionEntity

@Dao
interface TriviaDao {

    @Query("SELECT * FROM questions")
    fun getSavedQuestions(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveQuestion(question: QuestionEntity)

}