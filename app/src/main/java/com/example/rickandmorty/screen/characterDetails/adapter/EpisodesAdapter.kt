package com.example.rickandmorty.screen.characterDetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EpisodesAdapter(
    private val episodes: List<Int>
) : RecyclerView.Adapter<EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size
}
