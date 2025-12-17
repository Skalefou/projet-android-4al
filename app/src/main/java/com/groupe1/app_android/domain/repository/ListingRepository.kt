package com.groupe1.app_android.domain.repository

import com.groupe1.app_android.data.remote.models.CreateProposalDTO
import com.groupe1.app_android.domain.models.Listing

interface ListingRepository {
    suspend fun getAllListing(): List<Listing>
    suspend fun getListingById(id: Long): Listing?
    suspend fun postListing(listing: CreateProposalDTO): Listing
}