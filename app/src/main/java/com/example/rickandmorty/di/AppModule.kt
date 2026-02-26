package com.example.rickandmorty.di

import com.example.rickandmorty.BuildConfig
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.gemini.GeminiApi
import com.example.rickandmorty.utils.Constants.Companion.BASE_URL
import com.example.rickandmorty.utils.Constants.Companion.GEMINI_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    @RickAndMortyRetrofit
    fun provideRickAndMortyOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    @GeminiRetrofit
    fun provideGeminiOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.GEMINI_BEARER}")
                .build()
            chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    @RickAndMortyRetrofit
    fun provideRickAndMortyRetrofit(
        @RickAndMortyRetrofit okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    @GeminiRetrofit
    fun provideGeminiRetrofit(
        @GeminiRetrofit okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(GEMINI_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideCharacterApi(@RickAndMortyRetrofit retrofit: Retrofit): CharacterApi =
        retrofit.create(CharacterApi::class.java)

    @Singleton
    @Provides
    fun provideGeminiApi(@GeminiRetrofit retrofit: Retrofit): GeminiApi =
        retrofit.create(GeminiApi::class.java)
}
