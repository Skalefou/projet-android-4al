package com.groupe1.app_android.services

import com.groupe1.app_android.BuildConfig
import com.groupe1.app_android.networks.NetworkModule

object FilterAdService {
    private val searchBarCityApi = NetworkModule.searchBarCityApi

    suspend fun searchCities(q: String): List<String> {
        val res = searchBarCityApi.searchCities(
            query = q,
            token = BuildConfig.MAPBOX_TOKEN
        )
        return res.features.mapNotNull { it.properties?.fullAddress ?: it.properties?.name }.distinct()
    }
}