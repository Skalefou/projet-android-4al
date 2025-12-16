package com.groupe1.app_android.data.remote

import com.groupe1.app_android.data.mapper.mapListingDtoToListing
import com.groupe1.app_android.data.remote.services.ListingApiService
import com.groupe1.app_android.networks.NetworkModule
import retrofit2.Retrofit

open class ListingRemoteDataSource(
    api: Retrofit = NetworkModule.api
) {
    private val listingService = api.create(ListingApiService::class.java)

    open suspend fun fetchListings() = listingService.getAllListings().map { mapListingDtoToListing(it) }
    open suspend fun fetchListingById(id: Long) = mapListingDtoToListing(listingService.getListingById(id))
    open suspend fun fetchFilteredListings(criteriaMap: Map<String, String>) = listingService.getFilteredListings(criteriaMap).map { mapListingDtoToListing(it) }
}