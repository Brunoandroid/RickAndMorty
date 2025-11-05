package com.example.rickandmorty.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    val loading: LiveData<Boolean>
        get() = mLoadingChanged

    protected val mLoadingChanged = MutableLiveData<Boolean>()

    protected fun defaultLaunch(
        loadingLiveData: LiveData<Boolean> = mLoadingChanged,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            try {
                loadingLiveData.postValue(true)
                block.invoke(this)
                loadingLiveData.postValue(false)
            } catch (e: Exception) {
                loadingLiveData.postValue(false)
            }
        }
    }

    protected fun <T> LiveData<T>.postValue(data: T){
        if (this is MutableLiveData<T>) {
            postValue(data)
        }
    }

}