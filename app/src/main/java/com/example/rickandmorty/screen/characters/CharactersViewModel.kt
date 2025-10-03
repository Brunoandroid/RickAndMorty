package com.example.rickandmorty.screen.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.data.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharactersRepository
) : ViewModel() {

    val characters: Flow<PagingData<Result>> =
        repository.getAllCharacters().cachedIn(viewModelScope)
}
