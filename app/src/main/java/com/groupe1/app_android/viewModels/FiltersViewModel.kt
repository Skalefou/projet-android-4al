package com.groupe1.app_android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.data.remote.models.SearchFilters
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class FiltersViewModel(
    private val useCases: ListingUseCases
): ViewModel() {

    private val _filters = MutableStateFlow(SearchFilters())
    val filters: StateFlow<SearchFilters> = _filters

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun updateCity(city: String) {
        _filters.update { it.copy(city = city) }
    }

    fun updateDates(checkIn: Long?, checkOut: Long?) {
        _filters.update { it.copy(checkIn = checkIn, checkOut = checkOut) }
    }

    fun updateTravellers(adults: Int, children: Int, babies: Int, pets: Int) {
        _filters.update {
            it.copy(
                adults = adults,
                children = children,
                babies = babies,
                pets = pets
            )
        }
    }

    fun updateProperty(
        type: String?,
        minPrice: Int,
        maxPrice: Int,
        rooms: Int,
        bathrooms: Int,
        beds: Int
    ) {
        _filters.update {
            it.copy(
                propertyType = type,
                minPrice = minPrice,
                maxPrice = maxPrice,
                minRooms = rooms,
                minBathrooms = bathrooms,
                minBeds = beds
            )
        }
    }

    suspend fun getListingsFiltered(): List<Listing> {
        _error.value = null

        return try {
            useCases.getFilteredListings(filters.value)
        } catch (e: Exception) {
            _error.value = "Erreur de chargement des données. Error: ${e.message}"
            emptyList()
        }
    }
}
