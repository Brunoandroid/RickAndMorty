package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.model.character.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CharacterApi {

    @POST("character")
    suspend fun getCharacters(): Response<CharacterResponse?>

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<CharacterResponse?>

}