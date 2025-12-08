package com.groupe1.app_android.api;

import com.groupe1.app_android.dtos.AuthResponseDTO
import com.groupe1.app_android.dtos.LoginUserDTO
import com.groupe1.app_android.dtos.RegisterUserDTO

import retrofit2.http.Body;
import retrofit2.http.POST;

interface UserApi {
    @POST("/api/users/register")
    suspend fun register(@Body body: RegisterUserDTO): AuthResponseDTO

    @POST("/api/users/login")
    suspend fun login(@Body body: LoginUserDTO): AuthResponseDTO
}
