package com.groupe1.app_android.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val listingUseCases: ListingUseCases
) : ViewModel(
) {
    private val _remoteFavorites = MutableStateFlow<List<Listing>>(emptyList())
    val remoteFavorites = _remoteFavorites.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            try {
                _remoteFavorites.value = listingUseCases.getAllMyFavorites()
                _error.value = null
                Log.d("FavoritesViewModel", "Favorites: ${_remoteFavorites.value}")
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }
}