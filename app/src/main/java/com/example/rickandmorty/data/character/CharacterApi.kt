package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.model.character.CharacterResponse
import retrofit2.Response
import retrofit2.http.POST

interface CharacterApi {

    @POST("character")
    suspend fun getCharacters(): Response<CharacterResponse?>

}