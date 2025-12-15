package com.groupe1.app_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.groupe1.app_android.data.repository.FavoriteRepositoryImpl
import com.groupe1.app_android.data.repository.FilterListingRepositoryImpl
import com.groupe1.app_android.data.repository.ListingRepositoryImpl
import com.groupe1.app_android.domain.usecase.listings.GetAllListingUseCase
import com.groupe1.app_android.domain.usecase.listings.GetFilteredListingsUseCase
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import com.groupe1.app_android.domain.usecase.listings.favorites.GetAllMyFavoritesUseCase
import com.groupe1.app_android.domain.usecase.listings.favorites.IsFavoriteUseCase
import com.groupe1.app_android.domain.usecase.listings.favorites.LikeListingUseCase
import com.groupe1.app_android.domain.usecase.listings.favorites.UnlikeListingUseCase
import com.groupe1.app_android.ui.navigation.AppNav
import com.groupe1.app_android.ui.theme.ProjetandroidTheme
import com.groupe1.app_android.viewModels.FiltersViewModel
import com.groupe1.app_android.viewModels.ListingsViewModel

class MainActivity : ComponentActivity() {
    // Listings
    private val listingRepository = ListingRepositoryImpl()
    private val favoriteRepository = FavoriteRepositoryImpl()

    private val filterListingRepository = FilterListingRepositoryImpl()

    private val listingUseCases = ListingUseCases(
        getAllListing = GetAllListingUseCase(listingRepository),
        getAllMyFavorites = GetAllMyFavoritesUseCase(favoriteRepository),
        isFavorite = IsFavoriteUseCase(favoriteRepository),
        likeListing = LikeListingUseCase(favoriteRepository),
        unlikeListing = UnlikeListingUseCase(favoriteRepository),
        getFilteredListings = GetFilteredListingsUseCase(filterListingRepository)
    )
    private val listingsViewModel = ListingsViewModel(listingUseCases)

    private val filtersViewModel = FiltersViewModel(listingUseCases)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetandroidTheme(dynamicColor = false) {
                Surface {
                    val nav = rememberNavController()
                    AppNav(
                        nav,
                        listingsViewModel = listingsViewModel,
                        filtersViewModel = filtersViewModel
                    )
                }
            }
        }
    }
}