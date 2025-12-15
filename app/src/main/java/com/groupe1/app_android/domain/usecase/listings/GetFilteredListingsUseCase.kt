package com.groupe1.app_android.domain.usecase.listings

import com.groupe1.app_android.data.remote.models.SearchFilters
import com.groupe1.app_android.domain.repository.FilterListingRepository

class GetFilteredListingsUseCase (private val filterListingRepository: FilterListingRepository) {
    suspend operator fun invoke(criteria: SearchFilters) = filterListingRepository.getFilteredListings(criteria)
}