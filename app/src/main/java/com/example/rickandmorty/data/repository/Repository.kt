package com.example.rickandmorty.data.repository

import androidx.lifecycle.liveData
import com.example.rickandmorty.data.api.RequestApi
import com.example.rickandmorty.exception.FalhaAoBuscarPersonagensException
import com.example.rickandmorty.exception.FalhaNaComunicacaoComApiException
import com.example.rickandmorty.utils.Resultado
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.net.ConnectException
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val requestApi: RequestApi) {

    suspend fun getCharacteres() = liveData {
        try {
            val resposta = requestApi.getCharacters()
            if (resposta.isSuccessful) {
                emit(Resultado.Sucesso(dado = resposta.body()?.results))
            } else {
                emit(Resultado.Erro(exception = FalhaAoBuscarPersonagensException()))
            }
        } catch (e: ConnectException) {
            emit(Resultado.Erro(exception = FalhaNaComunicacaoComApiException()))
        }catch (e: Exception){
            emit(Resultado.Erro(exception = e))
        }
    }

}