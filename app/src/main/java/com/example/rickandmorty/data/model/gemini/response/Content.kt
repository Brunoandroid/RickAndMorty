package com.example.rickandmorty.data.model.gemini.response

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("parts")
    val parts: List<Part>
)
