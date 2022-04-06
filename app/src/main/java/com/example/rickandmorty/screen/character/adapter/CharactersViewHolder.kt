package com.example.rickandmorty.screen.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.databinding.AdapterCharacterBinding

class CharactersViewHolder(
    private val binding: AdapterCharacterBinding,
    private val listener: CharacterPagingAdapter.Listerner?
): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: CharacterPagingAdapter.Listerner?
        ): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(binding, listener)
        }
    }

    private var character: Result? = null

    init {

        itemView.setOnClickListener {
            val cardCharacter = character
            if (cardCharacter != null) {
                listener?.onCardClicked(cardCharacter, bindingAdapterPosition)
            }
        }

    }

    fun bind(character: Result?) {
        this.character = character

        character?.let {
            binding.cvCharacter.setData(
                nameCharacter = it.name,
                imageUrl = it.image
            )
        }
    }

}