package com.example.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.screen.characters.CharactersPagingSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class CharactersRepository @Inject constructor(
    private val characterApi: CharacterApi
    ) {

    fun getAllCharacters(): LiveData<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(characterApi)
            }
        ).liveData

    }



}