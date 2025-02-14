package com.ev.bluepixel.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://opentdb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitJokes() = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}