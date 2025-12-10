package com.groupe1.app_android.data.remote

import android.util.Patterns
import com.groupe1.app_android.dtos.AuthResponseDTO
import com.groupe1.app_android.dtos.LoginUserDTO
import com.groupe1.app_android.data.remote.models.RegisterUserDTO
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.networks.NetworkModule

object UserRemoteDataSource {
    private val userApi = NetworkModule.userApi

    suspend fun registerUser(registerUser: RegisterUserDTO): AuthResponseDTO {
        if (registerUser.firstName.isBlank()) {
            throw IllegalArgumentException("Le prénom ne peut pas être vide.")
        }
        if (registerUser.lastName.isBlank()) {
            throw IllegalArgumentException("Le nom ne peut pas être vide.")
        }
        if (registerUser.email.isBlank()) {
            throw IllegalArgumentException("L'email ne peut pas être vide.")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(registerUser.email).matches()) {
            throw IllegalArgumentException("L'email n'est pas valide.")
        }
        if (!Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$").matches(registerUser.password)  && registerUser.password.isNotBlank()) {
            throw IllegalArgumentException("Le mot de passe doit faire au moins 8 caractères avec minuscule, majuscule et chiffre.")
        }

        return userApi.register(registerUser)
    }

    suspend fun loginUser(loginUser : LoginUserDTO): AuthResponseDTO {
        if (loginUser.email.isBlank()) {
            throw IllegalArgumentException("L'email ne peut pas être vide.")
        }
        if (loginUser.password.isBlank()) {
            throw IllegalArgumentException("Le mot de passe ne peut pas être vide.")
        }

        return userApi.login(loginUser)
    }
}
