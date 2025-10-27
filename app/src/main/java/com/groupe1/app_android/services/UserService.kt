package com.groupe1.app_android.services

import com.groupe1.app_android.dtos.RegisterUserDTO
import com.groupe1.app_android.models.User
import com.groupe1.app_android.repository.UserRepository

object UserService {
    fun registerUser(registerUser: RegisterUserDTO): User {
        if (registerUser.firstName.isBlank()) {
            throw IllegalArgumentException("Le prénom ne peut pas être vide.")
        }
        if (registerUser.lastName.isBlank()) {
            throw IllegalArgumentException("Le nom ne peut pas être vide.")
        }
        if (registerUser.email.isBlank()) {
            throw IllegalArgumentException("L'email ne peut pas être vide.")
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(registerUser.email).matches()) {
            throw IllegalArgumentException("L'email n'est pas valide.")
        }
        if (!Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$").matches(registerUser.password)  && registerUser.password.isNotBlank()) {
            throw IllegalArgumentException("Le mot de passe doit faire au moins 8 caractères avec minuscule, majuscule et chiffre.")
        }

        return UserRepository.registerUserPost(registerUser)
    }
}