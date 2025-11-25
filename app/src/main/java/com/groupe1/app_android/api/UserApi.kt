package com.groupe1.app_android.api

import com.groupe1.app_android.dtos.RegisterUserDTO
import com.groupe1.app_android.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @POST("/api/users/register")
    suspend fun register(@Body body: RegisterUserDTO): User

    @GET("/api/users/me")
    suspend fun me(): User

    @PUT("/api/users/{id}")
    suspend fun update(@Path("id") id: Long, @Body body: com.groupe1.app_android.dtos.UpdateUserDTO): User
}
