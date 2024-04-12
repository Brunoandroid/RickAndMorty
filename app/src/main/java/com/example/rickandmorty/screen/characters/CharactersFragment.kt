package com.example.rickandmorty.screen.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.screen.characters.adapter.CharacterPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val charactersViewModel: CharactersViewModel by viewModels()

    private lateinit var _bindingCharacters: FragmentCharactersBinding
    private val bindingCharacters: FragmentCharactersBinding get() = _bindingCharacters

    private val adapter = CharacterPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _bindingCharacters = FragmentCharactersBinding.inflate(inflater, container, false)

        getAllCharacters()

        observerData()

        adapter.listener = object : CharacterPagingAdapter.Listener {
            override fun onCardClicked(result: Result, position: Int) {
                println("$result \n Index: $position")
                val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                    result)
                findNavController().navigate(action)
            }
        }

        return bindingCharacters.root
    }

    private fun observerData() {
        charactersViewModel.seeAllCharacters.observe(viewLifecycleOwner) { characters ->
            viewLifecycleOwner.lifecycleScope.launch {
                setData(characters)
            }
        }
    }

    private suspend fun setData(data: PagingData<Result>) {
        bindingCharacters.recyclerView.adapter = adapter
        adapter.submitData(data)
    }

    private fun getAllCharacters() {
        lifecycleScope.launch {
            charactersViewModel.getAllCharacters()
        }
    }

}