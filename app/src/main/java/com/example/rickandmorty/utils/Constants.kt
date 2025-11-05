package com.example.rickandmorty.utils

class Constants {
    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val GEMINI_BASE_URL = "https://vpirmghssosejxstznfo.supabase.co/"

        //Temporary solution until backend implementation
        const val GEMINI_BEARER="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZwaXJtZ2hzc29zZWp4c3R6bmZvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjIyODk3MTMsImV4cCI6MjA3Nzg2NTcxM30.ucrQkZ9qB2r6zv7zIotEMoeHIO6Scku23k0m7EBklkQ"
        const val GEMINI_HOST = "vpirmghssosejxstznfo.supabase.co"

        const val STATUS_ALIVE = "alive"
        const val STATUS_DEAD = "dead"
        const val STATUS_UNKNOWN = "unknown"
    }

}