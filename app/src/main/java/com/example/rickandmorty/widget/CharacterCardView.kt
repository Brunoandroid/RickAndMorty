package com.example.rickandmorty.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.databinding.CharacterCardViewBinding

class CharacterCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
) : CardView(context, attrs) {

    private val binding: CharacterCardViewBinding = CharacterCardViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setData(
        nameCharacter: String?,
        imageUrl: String?
    ) {
        binding.tvName.text = nameCharacter
        val imageView = binding.imgCharacter
        Glide.with(imageView)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }

}