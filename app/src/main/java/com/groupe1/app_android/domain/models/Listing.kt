package com.groupe1.app_android.domain.models

data class Listing(
    val id: Long,
    val title: String,
    val description: String,
    val priceByNight: Double,
    val ownerId: Long,
    val ownerName: String
)