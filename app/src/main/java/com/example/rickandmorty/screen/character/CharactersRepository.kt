package com.example.rickandmorty.screen.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.character.CharacterApiHelper
import com.example.rickandmorty.data.exception.FalhaAoBuscarPersonagensException
import com.example.rickandmorty.data.exception.FalhaNaComunicacaoComApiException
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.utils.Resultado
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.net.ConnectException
import javax.inject.Inject

@ActivityRetainedScoped
class CharactersRepository @Inject constructor(
    private val characterApiHelper: CharacterApiHelper
    ) {

    suspend fun getCharacteres() = liveData {
        try {
            val resposta = characterApiHelper.getCharacters()
            if (resposta.isSuccessful) {
                emit(Resultado.Sucesso(dado = resposta.body()?.results))
            } else {
                emit(Resultado.Erro(exception = FalhaAoBuscarPersonagensException()))
            }
        } catch (e: ConnectException) {
            emit(Resultado.Erro(exception = FalhaNaComunicacaoComApiException()))
        } catch (e: Exception) {
            emit(Resultado.Erro(exception = e))
        }
    }

    fun getAllCharacters(): LiveData<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(characterApiHelper)
            }
        ).liveData

    }



}