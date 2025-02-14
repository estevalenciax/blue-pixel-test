package com.ev.bluepixel.jokes.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ev.bluepixel.jokes.data.model.Joke

@Entity(tableName = "jokes")
data class JokeEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val created_at: String = "",
    val icon_url: String = "",
    val url: String = "",
    val value: String = ""
)

fun List<JokeEntity>.toJokes(): List<Joke> {
    return this.map { entity ->
        Joke(
            created_at = entity.created_at,
            icon_url = entity.icon_url,
            id = entity.id,
            url = entity.url,
            value = entity.value
        )
    }
}