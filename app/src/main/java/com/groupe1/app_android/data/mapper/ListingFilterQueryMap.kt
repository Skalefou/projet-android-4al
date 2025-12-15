package com.groupe1.app_android.data.mapper

import com.groupe1.app_android.data.remote.models.SearchFilters

/**
 * Converts a SearchFilters object into a query parameter map
 * to be used with Retrofit @QueryMap.
 */
fun mapSearchFilterToQueryMap(filters: SearchFilters): Map<String, String> {
    return buildMap {
        if (filters.city.isNotBlank()) put("city", filters.city)

        filters.checkIn?.let { put("checkIn", it.toString()) }
        filters.checkOut?.let { put("checkOut", it.toString()) }

        put("adults", filters.adults.toString())
        put("children", filters.children.toString())
        put("babies", filters.babies.toString())
        put("pets", filters.pets.toString())

        if (!filters.propertyType.isNullOrBlank()) {
            put("propertyType", filters.propertyType)
        }

        put("minPrice", filters.minPrice.toString())
        put("maxPrice", filters.maxPrice.toString())

        if (filters.minRooms > 0) put("minRooms", filters.minRooms.toString())
        if (filters.minBathrooms > 0) put("minBathrooms", filters.minBathrooms.toString())
        if (filters.minBeds > 0) put("minBeds", filters.minBeds.toString())
    }
}

