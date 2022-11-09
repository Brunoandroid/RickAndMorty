package com.example.rickandmorty.screen.character

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.utils.Resultado

class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharactersRepository,
    application: Application
) : BaseViewModel(application) {

    val seeAllCharacters: MutableLiveData<PagingData<Result>> = MutableLiveData()

    fun getAllCharacters() {
        repository.getAllCharacters()
            .observeForever { seeAllCharacters.value = it }
    }

}
