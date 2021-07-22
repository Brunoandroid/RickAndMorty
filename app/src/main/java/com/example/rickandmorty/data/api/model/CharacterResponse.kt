package com.example.rickandmorty.data.api.model

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)