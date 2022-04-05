package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.model.character.CharacterResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class CharacterApiHelperImpl @Inject constructor(
    private var characterApi: CharacterApi
): CharacterApiHelper {

    override suspend fun getCharacters(): Response<CharacterResponse?> =
        characterApi.getCharacters()

    override suspend fun getAllCharacters(page: Int): Response<CharacterResponse?> =
        characterApi.getAllCharacters(page)

}