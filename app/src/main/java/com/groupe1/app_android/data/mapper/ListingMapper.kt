package com.groupe1.app_android.data.mapper

import com.groupe1.app_android.data.remote.models.ListingDto
import com.groupe1.app_android.domain.models.Listing

fun mapListingDtoToListing(listingDto: ListingDto): Listing {
    return Listing(
        id = listingDto.id,
        title = listingDto.title,
        description = listingDto.description,
        numberOfRooms = listingDto.numberOfRooms,
        numberOfBathrooms = listingDto.numberOfBathrooms,
        numberOfBed = listingDto.numberOfBed,
        hasWifi = listingDto.hasWifi,
        hasWashingMachine = listingDto.hasWashingMachine,
        hasAirConditioning = listingDto.hasAirConditioning,
        hasTv = listingDto.hasTv,
        hasParking = listingDto.hasParking,
        maxGuests = listingDto.maxGuests,
        address = listingDto.address,
        zipCode = listingDto.zipCode,
        city = listingDto.city,
        country = listingDto.country,
        firstImage = listingDto.firstImage,
        secondImage = listingDto.secondImage,
        thirdImage = listingDto.thirdImage,
        priceByNight = listingDto.priceByNight,
        ownerId = listingDto.ownerId,
        ownerName = listingDto.ownerName
    )
}