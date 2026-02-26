package com.example.rickandmorty.screen.characterDetails.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: GeminiRepository
) : BaseViewModel() {

    private val _summary = MutableLiveData<String>()
    val summary: LiveData<String> get() = _summary

    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> get() = _error

    fun fetchEpisodeSummary(prompt: String?) {
        defaultLaunch(
            block = {
                _error.postValue(null)
                val text = repository.getEpisodeInfo(prompt.orEmpty())
                _summary.postValue(text)
            },
            onError = { e ->
                _error.postValue(e)
            }
        )
    }
}
