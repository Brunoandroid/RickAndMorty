package com.example.rickandmorty.screen.character

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rickandmorty.utils.Resultado

class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharactersRepository,
    application: Application
): AndroidViewModel(application) {

    suspend fun getCharacters(): LiveData<Resultado<List<Result>?>> =
        repository.getCharacteres()

}
