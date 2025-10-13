package com.groupe1.app_android.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// TODO: à brancher sur un store
object AuthState {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun setLoginState(isLoggedIn: Boolean) {
        _isLoggedIn.value = isLoggedIn
    }
}