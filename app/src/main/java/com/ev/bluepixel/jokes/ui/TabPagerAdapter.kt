package com.ev.bluepixel.jokes.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> JokesFragment()
            1 -> FavoriteJokesFragment()
            else -> JokesFragment()
        }
    }
}