package com.example.rickandmorty.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    lateinit var _bindingCharacters: FragmentCharactersBinding
    val bindingCharacters: FragmentCharactersBinding get() = _bindingCharacters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _bindingCharacters = FragmentCharactersBinding.inflate(inflater, container, false)


        return bindingCharacters.root
    }

}