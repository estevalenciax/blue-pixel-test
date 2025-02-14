package com.ev.bluepixel.core.di

import com.ev.bluepixel.jokes.data.network.JokeClient
import com.ev.bluepixel.trivia.data.network.TriviaClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @Named("TriviaRetrofit")
    fun provideTriviaRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://opentdb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideTriviaClient(@Named("TriviaRetrofit") retrofit: Retrofit): TriviaClient {
        return retrofit.create(TriviaClient::class.java)
    }

    @Singleton
    @Provides
    @Named("JokesRetrofit")
    fun provideJokeRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideJokeClient(@Named("JokesRetrofit") retofit: Retrofit) : JokeClient {
        return retofit.create(JokeClient::class.java)
    }

}