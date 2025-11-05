package com.example.rickandmorty.screen.characterDetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EpisodesAdapter(
    private val characterName: String,
    private val episodes: List<Int>,
    private val onEpisodeClick: (String, Int) -> Unit
) : RecyclerView.Adapter<EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        val episodeNumber = episodes[position]
        holder.bind(episodeNumber)
        holder.itemView.setOnClickListener { onEpisodeClick(characterName, episodeNumber) }
    }

    override fun getItemCount(): Int = episodes.size
}
