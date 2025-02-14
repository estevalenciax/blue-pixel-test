package com.ev.bluepixel.core

import android.app.Application
import androidx.room.Room
import com.ev.bluepixel.core.room.TriviaDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PruebaApp: Application() {
//    companion object {
//        lateinit var datablase: TriviaDatabase
//    }

//    override fun onCreate() {
//        super.onCreate()
//        datablase = Room.databaseBuilder(this, TriviaDatabase::class.java, "prueba-db").build()
//    }
}