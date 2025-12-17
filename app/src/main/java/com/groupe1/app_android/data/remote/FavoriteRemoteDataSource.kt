package com.groupe1.app_android.data.remote

import com.groupe1.app_android.data.mapper.mapListingDtoToListing
import com.groupe1.app_android.data.remote.services.FavoriteApiService
import com.groupe1.app_android.data.remote.services.ListingApiService
import com.groupe1.app_android.networks.NetworkModule
import retrofit2.Retrofit

open class FavoriteRemoteDataSource(
    api: Retrofit = NetworkModule.api
) {
    private val favoriteService = api.create(FavoriteApiService::class.java)

    open suspend fun fetchFavorites() = favoriteService.getFavoriteListings().map { mapListingDtoToListing(it) }
    open suspend fun fetchFavoriteById(id: Long) = favoriteService.isFavorite(id).isFavorite
    open suspend fun likeListing(id: Long) = mapListingDtoToListing(favoriteService.likeListing(id))
    open suspend fun unlikeListing(id: Long) = favoriteService.unlikeListing(id)
}