package com.example.rickandmorty.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.databinding.CharacterCardViewBinding
import com.example.rickandmorty.utils.StatusColorUtil

class CharacterCardView(
    context: Context,
    attrs: AttributeSet?,
) : CardView(context, attrs) {

    private val binding: CharacterCardViewBinding = CharacterCardViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setData(
        nameCharacter: String?,
        imageUrl: String?,
        status: String
    ) {
        binding.tvName.text = nameCharacter
        val imgCharacter = binding.imgCharacter
        val imgVisibleCharacter = binding.imgVisibleCharacter
        Glide.with(imgCharacter)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgCharacter)

        val colorRes = StatusColorUtil.getColorForStatus(status)
        imgVisibleCharacter.setColorFilter(
            ContextCompat.getColor(context, colorRes)
        )
    }

}