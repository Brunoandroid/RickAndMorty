package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.POST

interface RequestApi {

    @POST("character")
    suspend fun getCharacters(): Response<CharacterResponse?>

}