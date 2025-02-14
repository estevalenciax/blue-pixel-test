package com.ev.bluepixel.trivia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.bluepixel.data.Result
import com.ev.bluepixel.trivia.data.model.Question
import com.ev.bluepixel.trivia.data.TriviaRepository
import kotlinx.coroutines.launch

class TriviaViewModel: ViewModel() {
    private val repository = TriviaRepository()

    private val _question = MutableLiveData(Question())
    val question : LiveData<Question> = _question

    private val _selectedAnswer = MutableLiveData("")
    val selectedAnswer : LiveData<String> = _selectedAnswer

    private val _isCorrectAnswer = MutableLiveData(false)
    val isCorrectAnswer : LiveData<Boolean> = _isCorrectAnswer

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    private val _showError = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    fun getQuestion() {
        viewModelScope.launch {
            _isLoading.value = true

            when(val result = repository.getQuestions()) {
                is Result.Success -> {
                    val questions = result.data
                    if (questions.isNotEmpty()) {
                        val savedQuestions = repository.getQuestionsFromRoom()
                        val isQuestionAlreadySaved = savedQuestions.any { it.question == questions[0].question }
                        if (isQuestionAlreadySaved) {
                            getQuestion()
                            return@launch
                        }
                        _selectedAnswer.value = ""
                        _isCorrectAnswer.value = false
                        questions[0].answers = questions[0].answers.shuffled()
                        _question.value = questions[0]
                        _errorMessage.value = ""
                        _showError.value = false
                    }
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message
                    _showError.value = true
                }
            }
            _isLoading.value = false
        }
    }

    fun setSelectedAnswer(answer: String) {
        _selectedAnswer.value = answer
        checkAnswer()
        saveQuestion()
    }

    fun checkAnswer() {
        val correctAnswer = _question.value!!.correct_answer
        _isCorrectAnswer.value = correctAnswer == _selectedAnswer.value!!
    }

    fun saveQuestion() {
        viewModelScope.launch {
            repository.saveQuestion(_question.value!!)
        }
    }

}