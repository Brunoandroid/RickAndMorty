package com.example.rickandmorty.screen.characters.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.character.Character

class CharacterPagingAdapter :
    PagingDataAdapter<Character, CharactersViewHolder>(CHARACTER_ITEM_COMPARATOR) {

    companion object {
        private val CHARACTER_ITEM_COMPARATOR =
            object : DiffUtil.ItemCallback<Character>() {
                override fun areItemsTheSame(
                    oldItem: Character,
                    newItem: Character
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Character,
                    newItem: Character
                ): Boolean = oldItem == newItem

            }
    }

    var listener: Listener? = null

    interface Listener {
        fun onCardClicked(character: Character, position: Int)
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
