package com.example.rickandmorty.screen.characterDetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EpisodesAdapter(
    private val episodes: List<Int>,
    private val onEpisodeClick: (Int) -> Unit
) : RecyclerView.Adapter<EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        val episodeNumber = episodes[position]
        holder.bind(episodeNumber)
        holder.itemView.setOnClickListener { onEpisodeClick(episodeNumber) }
    }

    override fun getItemCount(): Int = episodes.size
}
