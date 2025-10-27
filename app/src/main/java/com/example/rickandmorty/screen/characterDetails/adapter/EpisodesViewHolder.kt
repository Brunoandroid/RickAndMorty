package com.example.rickandmorty.screen.characterDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemEpisodeBinding

class EpisodesViewHolder(
    private val binding: ItemEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): EpisodesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemEpisodeBinding.inflate(inflater, parent, false)
            return EpisodesViewHolder(binding)
        }
    }

    fun bind(episodeNumber: Int) {
        binding.tvEpisodeNumber.text = binding.root.context.getString(R.string.episode_number, episodeNumber)
    }
}