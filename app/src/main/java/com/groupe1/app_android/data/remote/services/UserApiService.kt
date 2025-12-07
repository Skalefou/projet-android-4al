package com.groupe1.app_android.data.remote.services

import com.groupe1.app_android.data.remote.models.RegisterUserDTO
import com.groupe1.app_android.domain.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/users/register")
    suspend fun register(@Body body: RegisterUserDTO): User
}