package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.model.character.Character
import com.example.rickandmorty.screen.characters.CharactersPagingSource
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Singleton
class CharactersRepository @Inject constructor(
    private val characterApi: CharacterApi
) {

    fun getAllCharacters(): Flow<PagingData<Character>> {
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