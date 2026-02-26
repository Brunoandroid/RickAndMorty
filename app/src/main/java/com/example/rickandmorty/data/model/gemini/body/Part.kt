package com.example.rickandmorty.data.model.gemini.body

import com.google.gson.annotations.SerializedName

data class Part(
    @SerializedName("text")
    val text: String
)
