package com.ev.bluepixel.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TriviaDao {

    @Query("SELECT * FROM questions")
    fun getSavedQuestions(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveQuestion(question: QuestionEntity)

}