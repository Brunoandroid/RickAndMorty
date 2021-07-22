package com.example.rickandmorty.ui.main.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.api.model.Result
import com.example.rickandmorty.data.repository.Repository
import com.example.rickandmorty.utils.Resultado


class CharactersViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    suspend fun getCharacters(): LiveData<Resultado<List<Result>?>> =
        repository.getCharacteres()
}
