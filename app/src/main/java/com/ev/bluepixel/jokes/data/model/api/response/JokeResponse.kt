package com.ev.bluepixel.jokes.data.model.api.response

import com.ev.bluepixel.jokes.data.model.Joke

data class JokeResponse(
    val categories: List<Any>,
    val created_at: String,
    val icon_url: String,
    val id: String,
    val updated_at: String,
    val url: String,
    val value: String
)

fun JokeResponse.toJoke(): Joke {
    return Joke(
        created_at = this.created_at,
        icon_url = this.icon_url,
        id = this.id,
        url = this.url,
        value = this.value
    )
}