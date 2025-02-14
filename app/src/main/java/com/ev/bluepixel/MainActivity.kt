package com.ev.bluepixel

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ev.bluepixel.databinding.ActivityMainBinding
import com.ev.bluepixel.trivia.ui.TriviaViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: TriviaViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAnswerOne.setOnClickListener(this)
        binding.btnAnswerTwo.setOnClickListener(this)
        binding.btnAnswerThree.setOnClickListener(this)
        binding.btnAnswerFour.setOnClickListener(this)
        binding.btnNextQuestion.setOnClickListener(this)

        viewModel.question.observe(this) {
            binding.tvQuestion.text = it.question
            if (it.answers.size == 4) {
                binding.btnAnswerOne.text = it.answers[0]
                binding.btnAnswerTwo.text = it.answers[1]
                binding.btnAnswerThree.text = it.answers[2]
                binding.btnAnswerFour.text = it.answers[3]
            }

        }

        viewModel.isCorrectAnswer.observe(this) { isCorrectAnswer ->
            if (viewModel.selectedAnswer.value == "") {
                return@observe
            }
            if (isCorrectAnswer) {
                Snackbar.make(binding.root, "Correct answer", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, "Incorrect answer", Snackbar.LENGTH_SHORT).show()
            }
            binding.btnNextQuestion.visibility = View.VISIBLE
        }

        viewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.tvQuestion.visibility = if (it) View.GONE else View.VISIBLE
            binding.btnAnswerOne.visibility = if (it) View.GONE else View.VISIBLE
            binding.btnAnswerTwo.visibility = if (it) View.GONE else View.VISIBLE
            binding.btnAnswerThree.visibility = if (it) View.GONE else View.VISIBLE
            binding.btnAnswerFour.visibility = if (it) View.GONE else View.VISIBLE
            binding.btnNextQuestion.visibility = View.GONE

            binding.btnAnswerOne.setBackgroundColor(Color.WHITE)
            binding.btnAnswerTwo.setBackgroundColor(Color.WHITE)
            binding.btnAnswerThree.setBackgroundColor(Color.WHITE)
            binding.btnAnswerFour.setBackgroundColor(Color.WHITE)
        }

        viewModel.getQuestion()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnAnswerOne.id, binding.btnAnswerTwo.id, binding.btnAnswerThree.id, binding.btnAnswerFour.id -> {
                val button: Button = v as Button
                viewModel.setSelectedAnswer(button.text.toString())
                if (button.text.toString() == viewModel.question.value!!.correct_answer) {
                    button.setBackgroundColor(Color.GREEN)
                } else {
                    button.setBackgroundColor(Color.RED)
                }
            }
            binding.btnNextQuestion.id -> {
                viewModel.getQuestion()
            }

        }
    }
}