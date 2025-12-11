package com.groupe1.app_android.domain.usecase.listings.favorites

import com.groupe1.app_android.domain.repository.FavoriteRepository

class IsFavoriteUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(id: Long): Boolean = favoriteRepository.getFavoriteById(id)
}