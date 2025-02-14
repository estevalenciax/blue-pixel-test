package com.ev.bluepixel.jokes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ev.bluepixel.R
import com.ev.bluepixel.databinding.FragmentFavoriteJokesBinding
import com.ev.bluepixel.jokes.data.model.Joke


class FavoriteJokesFragment : Fragment() {

    private var _binding: FragmentFavoriteJokesBinding? = null
    private val binding get() = _binding!!

    private val itemList = mutableListOf<Joke>()
    private val adapter = FavoriteJokeAdapter(itemList)

    private val viewModel: JokeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        initObservers()


    }
    private fun initObservers() {
        viewModel.savedJokes.observe(viewLifecycleOwner) {
            itemList.clear()
            itemList.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        viewModel.getJokesSaved()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}