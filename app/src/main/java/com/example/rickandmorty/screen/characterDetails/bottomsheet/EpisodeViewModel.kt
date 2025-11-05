package com.example.rickandmorty.screen.characterDetails.bottomsheet

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    application: Application,
    private val repository: GeminiRepository
) : BaseViewModel(application) {

    private val _summary = MutableLiveData<String>()
    val summary: LiveData<String> get() = _summary

    private val _error = MutableLiveData<Unit?>()
    val error: LiveData<Unit?> get() = _error

    fun fetchEpisodeSummary(prompt: String?) {
        defaultLaunch {
            _error.postValue(null)
            val text = repository.getEpisodeInfo(prompt ?: "")
            _summary.postValue(text)
        }
    }
}