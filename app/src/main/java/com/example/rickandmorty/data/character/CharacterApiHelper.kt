package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.model.character.CharacterResponse
import retrofit2.Response

interface CharacterApiHelper {

    suspend fun getCharacters(): Response<CharacterResponse?>
    suspend fun getAllCharacters(page: Int): Response<CharacterResponse?>

}