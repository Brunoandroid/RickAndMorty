package com.example.rickandmorty.di

import com.example.rickandmorty.BuildConfig
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.gemini.GeminiApi
import com.example.rickandmorty.utils.Constants.Companion.BASE_URL
import com.example.rickandmorty.utils.Constants.Companion.GEMINI_BASE_URL
import com.example.rickandmorty.utils.Constants.Companion.GEMINI_HOST
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
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(logging)
        }

        builder.addInterceptor { chain ->
            val original = chain.request()
            val url = original.url

            if (url.host.contains(GEMINI_HOST)) {
                val token = BuildConfig.GEMINI_BEARER
                if (token.isNotBlank()) {
                    val newRequest = original.newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    chain.proceed(newRequest)
                } else {
                    chain.proceed(original)
                }
            } else {
                chain.proceed(original)
            }
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @RickAndMortyRetrofit
    fun provideRickAndMortyRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    @GeminiRetrofit
    fun provideGeminiRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GEMINI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterApi(@RickAndMortyRetrofit retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGeminiApi(@GeminiRetrofit retrofit: Retrofit): GeminiApi {
        return retrofit.create(GeminiApi::class.java)
    }
}