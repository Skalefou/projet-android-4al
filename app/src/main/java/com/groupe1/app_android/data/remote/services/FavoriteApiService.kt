package com.groupe1.app_android.data.remote.services

import com.groupe1.app_android.data.remote.models.IsFavoriteDTO
import com.groupe1.app_android.data.remote.models.ListingDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteApiService {
    @GET("/api/listings/favorites")
    suspend fun getFavoriteListings(): List<ListingDto>

    @POST("/api/listings/{id}/favorites")
    suspend fun likeListing(@Path("id") id: Long): ListingDto

    @DELETE("/api/listings/{id}/favorites")
    suspend fun unlikeListing(@Path("id") id: Long): Unit

    @GET("/api/listings/{id}/favorites")
    suspend fun isFavorite(@Path("id") id: Long): IsFavoriteDTO
}