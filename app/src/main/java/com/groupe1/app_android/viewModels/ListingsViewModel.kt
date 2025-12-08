package com.groupe1.app_android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ListingsViewModel(
    private val useCases: ListingUseCases
) : ViewModel() {
    private val _remoteListings = MutableStateFlow<List<Listing>>(emptyList())
    val remoteListings = _remoteListings.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getListingsFromRepo()
    }

    fun getListingsFromRepo() {
        viewModelScope.launch {
            try {
                _remoteListings.value = useCases.getAllListing()
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }

    fun getListingById(id: Long) = _remoteListings.value.find { it.id == id  }
}