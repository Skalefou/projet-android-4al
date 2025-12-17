package com.groupe1.app_android.domain.usecase.listings.favorites

import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.repository.FavoriteRepository

class GetAllMyFavoritesUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(): List<Listing> = favoriteRepository.getFavoriteListings()
}