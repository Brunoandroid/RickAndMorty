package com.example.rickandmorty.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.screen.character.adapter.CharacterPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    val charactersViewModel: CharactersViewModel by viewModels()

    lateinit var _bindingCharacters: FragmentCharactersBinding
    val bindingCharacters: FragmentCharactersBinding get() = _bindingCharacters

    val adapter = CharacterPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _bindingCharacters = FragmentCharactersBinding.inflate(inflater, container, false)

        getAllCharacters()

        observerData()

        adapter.listener = object : CharacterPagingAdapter.Listener {
            override fun onCardClicked(result: Result, position: Int) {
                result
                position
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