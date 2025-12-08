package com.groupe1.app_android.data.repository

import com.groupe1.app_android.data.remote.ListingRemoteDataSource
import com.groupe1.app_android.data.remote.FilterListingRemoteDataSource
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.FilterListingRepository
import com.groupe1.app_android.domain.repository.ListingRepository

class FilterListingRepositoryImpl(
    private val remoteDataSource: FilterListingRemoteDataSource = FilterListingRemoteDataSource()
) : FilterListingRepository {
    override suspend fun searchCities(query: String): List<String> = remoteDataSource.searchCities(query)
}