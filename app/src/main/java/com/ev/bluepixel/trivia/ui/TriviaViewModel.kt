package com.ev.bluepixel.trivia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.bluepixel.model.Question
import com.ev.bluepixel.trivia.data.TriviaRepository
import kotlinx.coroutines.launch

class TriviaViewModel: ViewModel() {
    private val repository = TriviaRepository()

    private val _question = MutableLiveData(Question())
    val question : LiveData<Question> = _question

    private val _selectedAnswer = MutableLiveData("")
    val selectedAnswer : LiveData<String> = _selectedAnswer

    private val _showCorrectAnswerButton = MutableLiveData(false)
    val showCorrectAnswerButton : LiveData<Boolean> = _showCorrectAnswerButton

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun getQuestion() {
        viewModelScope.launch {
            _isLoading.value = true
            val questions = repository.getQuestions()
            if (questions.isNotEmpty()) {
                _showCorrectAnswerButton.value = false
                _selectedAnswer.value = ""
                _question.value = questions[0]
            }
            _isLoading.value = false
        }
    }

    fun setSelectedAnswer(answer: String) {
        _selectedAnswer.value = answer
    }

    fun checkAnswer() {
        val correctAnswer = _question.value!!.correct_answer
        _showCorrectAnswerButton.value = correctAnswer == _selectedAnswer.value!!
    }

}