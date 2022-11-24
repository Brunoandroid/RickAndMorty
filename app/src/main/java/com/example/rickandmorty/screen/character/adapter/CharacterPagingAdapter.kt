package com.example.rickandmorty.screen.character.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.character.Result

class CharacterPagingAdapter :
    PagingDataAdapter<Result, CharactersViewHolder>(CHARACTER_ITEM_COMPARATOR) {

    companion object {
        private val CHARACTER_ITEM_COMPARATOR =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean = oldItem == newItem

            }
    }

    var listener: Listener? = null

    interface Listener {
        fun onCardClicked(result: Result, position: Int)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        return CharactersViewHolder.create(parent, listener)
    }
}