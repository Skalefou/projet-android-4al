package com.groupe1.app_android.data.remote

import com.groupe1.app_android.BuildConfig
import com.groupe1.app_android.data.remote.services.FilterListingService
import com.groupe1.app_android.networks.NetworkModule
import retrofit2.Retrofit

open class FilterListingRemoteDataSource(
    api: Retrofit = NetworkModule.searchBarCityApi
) {
    private val filterListingService = api.create(FilterListingService::class.java)

    open suspend fun searchCities(q: String): List<String> {
    val res = filterListingService.searchCities(
        query = q,
        token = BuildConfig.MAPBOX_TOKEN
    )
    return res.features.mapNotNull { it.properties?.fullAddress ?: it.properties?.name }.distinct()
}
}