package com.groupe1.app_android.domain.usecase.listings

import com.groupe1.app_android.data.remote.models.CreateProposalDTO
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.ListingRepository

class CreateProposalUseCase(private val listingRepository: ListingRepository) {
    suspend operator fun invoke(proposal: CreateProposalDTO): Listing = listingRepository.postListing(proposal)
}