package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.screen.characters.CharactersPagingSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class CharactersRepository @Inject constructor(
    private val characterApi: CharacterApi
) {

    fun getAllCharacters(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(characterApi)
            }
        ).flow
    }
}