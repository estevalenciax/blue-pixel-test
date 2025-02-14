package com.ev.bluepixel.jokes.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.bluepixel.data.Result
import com.ev.bluepixel.jokes.data.JokeRepository
import com.ev.bluepixel.jokes.data.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val repository : JokeRepository): ViewModel() {

    private val _joke = MutableLiveData(Joke())
    val joke: LiveData<Joke> = _joke

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _savedJokes = MutableLiveData<List<Joke>>(emptyList())
    val savedJokes: LiveData<List<Joke>> = _savedJokes

    private val _showError = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    fun getJoke() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val response = repository.getJoke()) {
                is Result.Success -> {
                    _joke.value = response.data
                    _errorMessage.value = ""
                    _showError.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = response.exception.message
                    _showError.value = true
                }
            }
            _isLoading.value = false
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