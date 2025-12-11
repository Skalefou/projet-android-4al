package com.groupe1.app_android.domain.usecase.listings.favorites

import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.FavoriteRepository

class LikeListingUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(id: Long): Listing = favoriteRepository.likeListing(id)
}