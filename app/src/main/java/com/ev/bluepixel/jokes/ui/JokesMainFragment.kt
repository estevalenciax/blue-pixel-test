package com.ev.bluepixel.jokes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ev.bluepixel.R
import com.ev.bluepixel.databinding.FragmentJokesMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class JokesMainFragment : Fragment() {

    private var _binding : FragmentJokesMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
        initTabLayout()


    }

    private fun setUpViewPager() {
        val adapter = TabPagerAdapter(this)
        binding.viewPager.adapter = adapter
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Chistes"
                1 -> "Favoritos"
                else -> ""

            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}