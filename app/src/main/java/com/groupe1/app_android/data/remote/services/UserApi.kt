package com.groupe1.app_android.data.remote.services

import com.groupe1.app_android.data.remote.models.RegisterUserDTO
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.dtos.AuthResponseDTO
import com.groupe1.app_android.dtos.LoginUserDTO
import com.groupe1.app_android.dtos.UpdateUserDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class RefreshRequest(val refreshToken: String)
data class TokenPair(val access: String, val refresh: String)
interface UserApi {
    @POST("/api/users/register")
    suspend fun register(@Body body: RegisterUserDTO): AuthResponseDTO

    @POST("/api/users/login")
    suspend fun login(@Body body: LoginUserDTO): AuthResponseDTO

//    @PUT("/api/users/{id}")
//    suspend fun update(@Path("id") id: Long, @Body body: UpdateUserDTO): User

    @POST("/api/users/refresh")
    suspend fun refresh(@Body body: RefreshRequest): TokenPair
}
