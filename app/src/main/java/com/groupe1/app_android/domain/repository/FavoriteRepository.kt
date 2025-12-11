package com.groupe1.app_android.domain.repository

import com.groupe1.app_android.domain.models.Listing

interface FavoriteRepository {
    suspend fun getFavoriteListings(): List<Listing>
    suspend fun getFavoriteById(id: Long): Boolean
    suspend fun likeListing(id: Long): Listing
    suspend fun unlikeListing(id: Long)
}