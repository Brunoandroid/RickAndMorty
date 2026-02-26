package com.example.rickandmorty.data.model.gemini.response

import com.google.gson.annotations.SerializedName

data class GeminiResponse(
    @SerializedName("candidates")
    val candidates: List<Candidate>
)
