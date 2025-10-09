package com.example.rickandmorty.screen.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private lateinit var _bindingCharacterDetails: FragmentCharacterDetailsBinding
    private val bindingCharacterDetails: FragmentCharacterDetailsBinding get() = _bindingCharacterDetails

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val character = args.character

        _bindingCharacterDetails =
            FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        val imageCharacter = bindingCharacterDetails.characterImageDetail
        Glide.with(imageCharacter)
            .load(character.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageCharacter)

        bindingCharacterDetails.tvCharacterDetail.text = character.name

        return bindingCharacterDetails.root

    }

}