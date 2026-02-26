package com.example.rickandmorty.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val loading: LiveData<Boolean>
        get() = _loading

    private val _loading = MutableLiveData<Boolean>()

    protected fun defaultLaunch(
        block: suspend CoroutineScope.() -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                block.invoke(this)
                _loading.postValue(false)
            } catch (e: CancellationException) {
                _loading.postValue(false)
                throw e
            } catch (e: Exception) {
                _loading.postValue(false)
                onError(e)
            }
        }
    }
}
