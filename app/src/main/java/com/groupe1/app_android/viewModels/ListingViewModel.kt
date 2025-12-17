package com.groupe1.app_android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListingViewModel(
    private val listingId: Long,
    private val useCases: ListingUseCases
) : ViewModel() {
    private val _remoteListing = MutableStateFlow<Listing?>(null)
    val remoteListing = _remoteListing.asStateFlow()

    private val _remoteIsFavorite = MutableStateFlow<Boolean>(false)
    val remoteIsFavorite = _remoteIsFavorite.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getListingById()
        isFavorite()
    }

    fun getListingById() {
        viewModelScope.launch {
            try {
                _remoteListing.value = useCases.getListing(listingId)
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }

    fun likeListing() {
        viewModelScope.launch {
            try {
                if(_remoteIsFavorite.value) {
                    useCases.unlikeListing(listingId)
                    _remoteIsFavorite.value = false
                } else {
                    useCases.likeListing(listingId)
                    _remoteIsFavorite.value = true
                }
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }

    fun isFavorite() {
        viewModelScope.launch {
            try {
                _remoteIsFavorite.value = useCases.isFavorite(listingId)
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }
}
