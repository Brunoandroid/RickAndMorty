package com.example.rickandmorty.data.model.gemini.response

import com.google.gson.annotations.SerializedName

data class Candidate(
    @SerializedName("content")
    val content: Content
)
