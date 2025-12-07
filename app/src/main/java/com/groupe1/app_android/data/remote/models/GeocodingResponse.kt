package com.groupe1.app_android.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    val features: List<GeocodingFeature>
)

@Serializable
data class GeocodingFeature(
    val properties: Properties? = null,
    @SerialName("full_address")
    val fullAddress: String? = null
)

@Serializable
data class Properties(
    @SerialName("name_preferred")
    val name: String? = null,
    @SerialName("full_address")
val fullAddress: String? = null
)