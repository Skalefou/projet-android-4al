package com.groupe1.app_android.domain.usecase.listings

import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.ListingRepository

class GetAllListingUseCase(private val listingRepository: ListingRepository) {
    suspend operator fun invoke(): List<Listing> = listingRepository.getAllListing()
}