package com.groupe1.app_android.data.repository

import com.groupe1.app_android.data.mapper.mapSearchFilterToQueryMap
import com.groupe1.app_android.data.remote.ListingRemoteDataSource
import com.groupe1.app_android.data.remote.SearchCityRemoteDataSource
import com.groupe1.app_android.data.remote.models.SearchFilters
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.FilterListingRepository
import com.groupe1.app_android.domain.repository.ListingRepository

class FilterListingRepositoryImpl(
    private val searchCityRemoteDataSource: SearchCityRemoteDataSource = SearchCityRemoteDataSource(),
    private val listingRemoteDataSource: ListingRemoteDataSource = ListingRemoteDataSource()
) : FilterListingRepository {
    override suspend fun searchCities(query: String): List<String> = searchCityRemoteDataSource.searchCities(query)
    override suspend fun getFilteredListings(criteria: SearchFilters): List<Listing> = listingRemoteDataSource.fetchFilteredListings(
        mapSearchFilterToQueryMap(criteria)
    )

}