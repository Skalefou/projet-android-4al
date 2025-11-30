package com.groupe1.app_android.data.repository

import com.groupe1.app_android.data.remote.ListingRemoteDataSource
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.ListingRepository

class ListingRepositoryImpl(
    private val remoteDataSource: ListingRemoteDataSource = ListingRemoteDataSource()
) : ListingRepository {
    override suspend fun getAllListing(): List<Listing> = remoteDataSource.fetchListings()
    override suspend fun getListingById(id: Long): Listing = remoteDataSource.fetchListingById(id)
}