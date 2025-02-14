package com.ev.bluepixel.jokes.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ev.bluepixel.jokes.data.model.room.JokeEntity

@Dao
interface JokeDao {

    @Query("SELECT * FROM jokes")
    suspend fun getJokes(): List<JokeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJoke(joke: JokeEntity)
}