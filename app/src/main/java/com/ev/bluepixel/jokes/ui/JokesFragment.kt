package com.ev.bluepixel.jokes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ev.bluepixel.R
import com.ev.bluepixel.databinding.FragmentJokesBinding
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentJokesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JokeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()

        viewModel.getJoke()
    }

    private fun initListeners() {
        binding.nextJokeBtn.setOnClickListener(this)
        binding.favoriteBtn.setOnClickListener(this)
    }

    private fun initObservers() {
        viewModel.joke.observe(viewLifecycleOwner) {
            binding.jokeTv.text = it.value
            if (it.value.isBlank()) {
                binding.favoriteBtn.visibility = View.GONE
                binding.favoriteBtn.isEnabled = false
            } else {
                binding.favoriteBtn.visibility = View.VISIBLE
                binding.favoriteBtn.isEnabled = true
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.jokeTv.visibility = if (it) View.GONE else View.VISIBLE
            binding.nextJokeBtn.visibility = if (it) View.GONE else View.VISIBLE
            binding.favoriteBtn.visibility = if (it) View.GONE else View.VISIBLE
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(binding.root, viewModel.errorMessage.value!!, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.nextJokeBtn.id -> {
                viewModel.getJoke()
            }
            binding.favoriteBtn.id -> {
                viewModel.saveJoke()
                Snackbar.make(binding.root, "Se agregó el chiste a fevoritos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}