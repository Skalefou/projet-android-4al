package com.groupe1.app_android.data.remote.models

data class SearchFilters(
    val city: String = "",
    val checkIn: Long? = null,
    val checkOut: Long? = null,
    val adults: Int = 1,
    val children: Int = 0,
    val babies: Int = 0,
    val pets: Int = 0,
    val propertyType: String? = "",        // Chambre / Appartement / Maison / ""
    val minPrice: Int = 1,
    val maxPrice: Int = 15_000,
    val minRooms: Int = 0,
    val minBathrooms: Int = 0,
    val minBeds: Int = 0
)

