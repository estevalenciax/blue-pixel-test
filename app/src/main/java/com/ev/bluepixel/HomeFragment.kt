package com.ev.bluepixel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ev.bluepixel.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToQuizBtn.setOnClickListener(this)
        binding.goToJokesBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.goToQuizBtn.id -> {
                findNavController().navigate(R.id.action_homeFragment_to_triviaFragment)
            }
            binding.goToJokesBtn.id -> {
                findNavController().navigate(R.id.action_homeFragment_to_jokesFragment)
            }
        }
    }

}