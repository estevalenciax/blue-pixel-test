package com.ev.bluepixel.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ev.bluepixel.model.room.QuestionEntity
import com.ev.bluepixel.model.room.TriviaDao

@Database(entities = arrayOf(QuestionEntity::class), version = 1)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao
}