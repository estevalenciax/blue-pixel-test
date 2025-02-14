package com.ev.bluepixel.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ev.bluepixel.jokes.data.model.room.JokeEntity
import com.ev.bluepixel.jokes.data.room.JokeDao
import com.ev.bluepixel.model.room.QuestionEntity
import com.ev.bluepixel.model.room.TriviaDao

@Database(entities = arrayOf(QuestionEntity::class, JokeEntity::class), version = 2)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao
    abstract fun jokeDao(): JokeDao
}