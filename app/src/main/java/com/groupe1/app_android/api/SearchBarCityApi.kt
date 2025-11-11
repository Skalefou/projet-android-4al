package com.groupe1.app_android.api

import com.groupe1.app_android.models.GeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBarCityApi {
    @GET("search/geocode/v6/forward")
    suspend fun searchCities(
        @Query("q") query: String,
        @Query("proximity") proximity: String = "ip",
        @Query("types") types: String = "region",
        @Query("access_token") token: String,
        @Query("language") language: String = "fr",
        @Query("limit") limit: Int = 8
    ): GeocodingResponse
}