package com.example.rickandmorty.data.model.character

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)