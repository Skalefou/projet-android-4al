package com.groupe1.app_android.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateProposalDTO(
    val title: String,
    val description: String,
    val type: String,
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
    val priceByNight: Int
)
