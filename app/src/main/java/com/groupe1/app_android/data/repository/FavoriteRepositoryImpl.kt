package com.groupe1.app_android.data.repository

import com.groupe1.app_android.data.remote.FavoriteRemoteDataSource
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(
    private val remoteDataSource: FavoriteRemoteDataSource = FavoriteRemoteDataSource()
) : FavoriteRepository {
    override suspend fun getFavoriteListings(): List<Listing> = remoteDataSource.fetchFavorites()
    override suspend fun getFavoriteById(id: Long): Boolean = remoteDataSource.fetchFavoriteById(id)
    override suspend fun likeListing(id: Long): Listing = remoteDataSource.likeListing(id)
    override suspend fun unlikeListing(id: Long) = remoteDataSource.unlikeListing(id)
}
