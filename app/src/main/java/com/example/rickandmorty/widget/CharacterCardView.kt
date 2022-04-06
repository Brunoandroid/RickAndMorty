package com.example.rickandmorty.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.rickandmorty.R
import com.google.android.material.textview.MaterialTextView

class CharacterCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs){

    private val tvName: MaterialTextView

    init {
        val context = LayoutInflater.from(context)
            .inflate(R.layout.character_card_view, this, false)

        tvName = context.findViewById(R.id.tvName)

        addView(context)
    }

    fun setData(
        nameCharacter: String?
    ) {
        tvName.text = nameCharacter
    }

}