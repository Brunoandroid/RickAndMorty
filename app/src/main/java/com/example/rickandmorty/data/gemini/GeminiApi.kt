package com.example.rickandmorty.data.gemini

import com.example.rickandmorty.data.model.gemini.body.GeminiBody
import com.example.rickandmorty.data.model.gemini.response.GeminiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GeminiApi {
    @POST("functions/v1/gemini")
    suspend fun generateContent(
        @Body body: GeminiBody
    ): Response<GeminiResponse>
}