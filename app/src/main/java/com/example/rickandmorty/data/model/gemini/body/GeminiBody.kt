package com.example.rickandmorty.data.model.gemini.body

import com.google.gson.annotations.SerializedName

data class GeminiBody(
    @SerializedName("contents")
    val contents: List<Content>
)
