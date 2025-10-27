package com.example.rickandmorty.screen.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.screen.characterDetails.adapter.EpisodesAdapter
import com.example.rickandmorty.utils.StatusColorUtil
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

        bindingCharacterDetails.tvStatusChip.apply {
            text = character.status
            val bgColor = StatusColorUtil.getColorForStatus(character.status)
            background.setTint(ContextCompat.getColor(requireContext(),bgColor))
        }

        bindingCharacterDetails.tvSpecies.text = character.species
        bindingCharacterDetails.tvGender.text = character.gender
        bindingCharacterDetails.tvOrigin.text = character.origin.name
        bindingCharacterDetails.tvLocation.text = character.location.name

        val episodeNumbers = character.episode.mapNotNull { url ->
            url.substringAfterLast('/').toIntOrNull()
        }
        bindingCharacterDetails.rvEpisodes.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = EpisodesAdapter(episodeNumbers)
            setHasFixedSize(true)
        }

        return bindingCharacterDetails.root
    }
}