package com.ev.bluepixel.jokes.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.bluepixel.jokes.data.JokeRepository
import com.ev.bluepixel.jokes.data.model.Joke
import kotlinx.coroutines.launch

class JokeViewModel: ViewModel() {

    private val repository = JokeRepository()

    private val _joke = MutableLiveData(Joke())
    val joke: LiveData<Joke> = _joke

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _savedJokes = MutableLiveData<List<Joke>>(emptyList())
    val savedJokes: LiveData<List<Joke>> = _savedJokes

    fun getJoke() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getJoke()
                _joke.value = response
            } catch (e: Exception) {
                Log.d("JokeViewModel", "Error al obtener la broma: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getJokesSaved() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getJokesFromRoom()
                _savedJokes.value = response
            } catch (e: Exception) {
                Log.d("JokeViewModel", "Error al obtener la broma: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }

    }

    fun saveJoke() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.saveJoke(_joke.value!!)
                } catch (e: Exception) {
                Log.d("JokeViewModel", "Error al obtener la broma: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}