package com.groupe1.app_android.session

import com.groupe1.app_android.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object UserStore {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun login(user: User) {
        _user.value = user
    }
    fun logout() {
        _user.value = null
    }
}