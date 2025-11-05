package com.example.rickandmorty.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickAndMortyRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeminiRetrofit
