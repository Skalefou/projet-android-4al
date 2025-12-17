package com.groupe1.app_android.domain.repository

import com.groupe1.app_android.data.remote.models.ListingDto
import com.groupe1.app_android.data.remote.models.SearchFilters
import com.groupe1.app_android.domain.models.Listing

interface FilterListingRepository {
    suspend fun searchCities(query: String): List<String>
    suspend fun getFilteredListings(criteria: SearchFilters): List<Listing>
}

