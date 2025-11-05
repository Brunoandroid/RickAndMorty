package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.gemini.GeminiApi
import com.example.rickandmorty.data.model.gemini.body.Content
import com.example.rickandmorty.data.model.gemini.body.GeminiBody
import com.example.rickandmorty.data.model.gemini.body.Part
import javax.inject.Inject

class GeminiRepository @Inject constructor(
    private val api: GeminiApi,
) {

    suspend fun getEpisodeInfo(prompt: String): String {
        val body = GeminiBody(
            contents = listOf(
                Content(parts = listOf(Part(text = prompt)))
            )
        )
        val response = api.generateContent(body)
        val text = response.body()
            ?.candidates
            ?.firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
        return text
            ?.replace("\n", " ")
            ?.replace("\\s+".toRegex(), " ")
            ?.trim()
            .orEmpty()

    }
}