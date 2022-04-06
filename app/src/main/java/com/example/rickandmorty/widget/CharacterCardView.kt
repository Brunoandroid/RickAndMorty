package com.example.rickandmorty.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharacterCardViewBinding

class CharacterCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
) : CardView(context, attrs) {

    companion object {
        const val STATUS_ALIVE = "Alive"
        const val STATUS_DEAD = "Dead"
        const val STATUS_UNKNOWN = "unknown"
    }

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

        when (status) {
            STATUS_ALIVE -> imgVisibleCharacter.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.greenPersonality
                )
            )
            STATUS_DEAD -> imgVisibleCharacter.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.redPersonality
                )
            )
            STATUS_UNKNOWN -> imgVisibleCharacter.setColorFilter(
                ContextCompat.getColor(context, R.color.darkGray)
            )
        }
    }

}