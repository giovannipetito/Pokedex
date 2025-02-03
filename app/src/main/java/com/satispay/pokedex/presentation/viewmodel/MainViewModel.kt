package com.satispay.pokedex.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    private val _keepSplashOpened: MutableState<Boolean> = mutableStateOf(false)
    var keepSplashOpened: State<Boolean> = _keepSplashOpened

    private val _firstAccess: MutableState<Boolean> = mutableStateOf(false)
    var firstAccess: State<Boolean> = _firstAccess

    var isLoading: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun setSplashOpened(state: Boolean) {
        _keepSplashOpened.value = state
    }

    fun setFirstAccess(firstAccess: Boolean) {
        _firstAccess.value = firstAccess
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}