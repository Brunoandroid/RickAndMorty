package com.example.rickandmorty.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.databinding.CharacterCardViewBinding
import com.example.rickandmorty.utils.StatusColorUtil

class CharacterCardView(
    context: Context,
    attrs: AttributeSet?,
) : ConstraintLayout(context, attrs) {

    private val binding: CharacterCardViewBinding = CharacterCardViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setData(
        nameCharacter: String?,
        imageUrl: String?,
        status: String
    ) {
        binding.tvName.text = nameCharacter
        binding.tvStatusBadge.text = status

        Glide.with(binding.imgCharacter)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgCharacter)

        val colorRes = StatusColorUtil.getColorForStatus(status)
        binding.tvStatusBadge.background.setTint(
            ContextCompat.getColor(context, colorRes)
        )
        binding.imgVisibleCharacter.background.setTint(
            ContextCompat.getColor(context, colorRes)
        )
    }
}
