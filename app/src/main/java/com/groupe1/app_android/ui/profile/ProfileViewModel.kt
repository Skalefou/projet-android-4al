package com.groupe1.app_android.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.dtos.UpdateUserDTO
import com.groupe1.app_android.networks.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    /*private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _uiMessage = MutableStateFlow<String?>(null)
    val uiMessage: StateFlow<String?> = _uiMessage.asStateFlow()

    fun loadUser() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val u = NetworkModule.userApi.me()
                _user.value = u
            } catch (t: Throwable) {
                _uiMessage.value = "Erreur chargement profil: ${t.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun updateUser(update: UpdateUserDTO) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val id = _user.value?.id ?: 0L
                val updated = NetworkModule.userApi.update(id, update)
                _user.value = updated
                _uiMessage.value = "Profil mis à jour"
            } catch (t: Throwable) {
                _uiMessage.value = "Erreur mise à jour: ${t.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun logout() {
        // simple: mettre à jour AuthState; la navigation est gérée par le route
        AuthState.setLoginState(false)
    }

    fun clearMessage() {
        _uiMessage.value = null
    }*/
}

