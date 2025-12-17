package com.groupe1.app_android.domain.usecase.listings

import com.groupe1.app_android.domain.usecase.listings.favorites.GetAllMyFavoritesUseCase
import com.groupe1.app_android.domain.usecase.listings.favorites.IsFavoriteUseCase
import com.groupe1.app_android.domain.usecase.listings.favorites.LikeListingUseCase
import com.groupe1.app_android.domain.usecase.listings.favorites.UnlikeListingUseCase

data class ListingUseCases(
    val getAllListing: GetAllListingUseCase,
    val createProposal: CreateProposalUseCase,
    val getListing: GetListingUseCase,
    val getAllMyFavorites: GetAllMyFavoritesUseCase,
    val isFavorite: IsFavoriteUseCase,
    val likeListing: LikeListingUseCase,
    val unlikeListing: UnlikeListingUseCase,
    val getFilteredListings: GetFilteredListingsUseCase
)
