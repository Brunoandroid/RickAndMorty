package com.example.rickandmorty.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.rickandmorty.databinding.CharacterCardViewBinding

class CharacterCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
) : CardView(context, attrs) {

    private val binding: CharacterCardViewBinding = CharacterCardViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setData(
        nameCharacter: String?
    ) {
        binding.tvName.text = nameCharacter
    }

}