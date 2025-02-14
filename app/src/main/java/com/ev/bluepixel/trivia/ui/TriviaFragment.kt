package com.ev.bluepixel.trivia.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.ev.bluepixel.R
import com.ev.bluepixel.databinding.FragmentTriviaBinding
import com.google.android.material.snackbar.Snackbar


class TriviaFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentTriviaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TriviaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTriviaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAnswerOne.setOnClickListener(this)
        binding.btnAnswerTwo.setOnClickListener(this)
        binding.btnAnswerThree.setOnClickListener(this)
        binding.btnAnswerFour.setOnClickListener(this)
        binding.btnNextQuestion.setOnClickListener(this)

        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvQuestion.text = it.question
            if (it.answers.size == 4) {
                binding.btnAnswerOne.text = it.answers[0]
                binding.btnAnswerTwo.text = it.answers[1]
                binding.btnAnswerThree.text = it.answers[2]
                binding.btnAnswerFour.text = it.answers[3]
            }

        }

        viewModel.isCorrectAnswer.observe(viewLifecycleOwner) { isCorrectAnswer ->
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

        viewModel.isLoading.observe(viewLifecycleOwner) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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