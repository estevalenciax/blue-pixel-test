package com.ev.bluepixel.jokes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ev.bluepixel.databinding.LayoutItemListJokeBinding
import com.ev.bluepixel.jokes.data.model.Joke

class FavoriteJokeAdapter(private val itemList: List<Joke>) : RecyclerView.Adapter<FavoriteJokeAdapter.FavoriteJokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteJokeViewHolder {
        val view = LayoutItemListJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteJokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteJokeViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class FavoriteJokeViewHolder(private val binding: LayoutItemListJokeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.jokeTv.text = joke.value
        }
    }
}