package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.model.character.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharacterResponse

}