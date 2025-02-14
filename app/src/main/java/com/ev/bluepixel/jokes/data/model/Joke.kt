package com.ev.bluepixel.jokes.data.model

import com.ev.bluepixel.jokes.data.model.room.JokeEntity

data class Joke(
    val created_at: String = "",
    val icon_url: String = "",
    val id: String = "",
    val url: String = "",
    val value: String = ""
)

fun Joke.toJokeEntity(): JokeEntity {
    return JokeEntity(
        created_at = this.created_at,
        icon_url = this.icon_url,
        id = this.id,
        url = this.url,
        value = this.value
    )
}