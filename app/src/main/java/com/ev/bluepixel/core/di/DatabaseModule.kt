package com.ev.bluepixel.core.di

import android.content.Context
import androidx.room.Room
import com.ev.bluepixel.core.room.TriviaDatabase
import com.ev.bluepixel.jokes.data.room.JokeDao
import com.ev.bluepixel.trivia.data.room.TriviaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTriviaDao(database: TriviaDatabase): TriviaDao {
        return database.triviaDao()
    }
    @Provides
    fun provideJokeDao(database: TriviaDatabase): JokeDao {
        return database.jokeDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TriviaDatabase {
        return Room.databaseBuilder(appContext, TriviaDatabase::class.java, "prueba-db").build()
    }
}