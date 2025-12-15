package com.groupe1.app_android.data.remote.services

import com.groupe1.app_android.data.remote.models.ListingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ListingApiService {
    @GET("/api/listings")
    suspend fun getAllListings(): List<ListingDto>

    @GET("/api/listings/{id}")
    suspend fun getListingById(@Path("id") id: Long): ListingDto

    @GET("/api/listings/search")
    suspend fun getFilteredListings(
        @QueryMap filters: Map<String, String>
    ): List<ListingDto>
}