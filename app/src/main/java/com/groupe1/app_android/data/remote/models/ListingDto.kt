package com.groupe1.app_android.data.remote.models


data class ListingDto(
    val id: Long,
    val title: String,
    val description: String,
    val numberOfRooms: Int,
    val numberOfBathrooms: Int,
    val numberOfBed: Int,
    val hasWifi: Boolean,
    val hasWashingMachine: Boolean,
    val hasAirConditioning: Boolean,
    val hasTv: Boolean,
    val hasParking: Boolean,
    val maxGuests: Int,
    val address: String,
    val zipCode: String,
    val city: String,
    val country: String,
    val firstImage: String,
    val secondImage: String?,
    val thirdImage: String?,
    val priceByNight: Int,
    val ownerId: Long,
    val ownerName: String
)