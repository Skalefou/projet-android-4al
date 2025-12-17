package com.groupe1.app_android.mapper

import com.groupe1.app_android.data.mapper.mapListingDtoToListing
import com.groupe1.app_android.data.remote.models.ListingDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ListingMapperTest {
    @Test
    fun `should map data from dto to listing`() {
        // Framework AAA

        // Arrange
        val dto = ListingDto(
            id = 1L,
            title = "Charming Cottage",
            description = "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Cillum dolore eu fugiat nulla pariatur excepteur sint occaecat cupidatat Cillum dolore eu fugiat nulla pariatur excepteur sint occaecat cupidatat Ut enim ad minim veniam quis nostrud exercitation ullamco laboris",
            numberOfRooms = 3,
            numberOfBathrooms = 1,
            numberOfBed = 3,
            hasWifi = true,
            hasWashingMachine = true,
            hasAirConditioning = true,
            hasTv = true,
            hasParking = true,
            maxGuests = 6,
            address = "Avenue de Gauthier",
            zipCode = "75001",
            city = "Paris",
            country = "France",
            firstImage = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/120251269.jpg?k=638701338fd3475774a6d0e01848f44d44a450b162680bd7d9e7207e5aeb2871&o=",
            secondImage = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/120251269.jpg?k=638701338fd3475774a6d0e01848f44d44a450b162680bd7d9e7207e5aeb2871&o=",
            thirdImage = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/120251269.jpg?k=638701338fd3475774a6d0e01848f44d44a450b162680bd7d9e7207e5aeb2871&o=",
            priceByNight = 120,
            ownerId = 10,
            ownerName = "Alice"
        )

        // Act
        val listing = mapListingDtoToListing(dto)

        // Assert
        assertEquals(dto.id, listing.id)
        assertEquals(dto.title, listing.title)
        assertEquals(dto.description, listing.description)
        assertEquals(dto.numberOfRooms, listing.numberOfRooms)
        assertEquals(dto.numberOfBathrooms, listing.numberOfBathrooms)
        assertEquals(dto.numberOfBed, listing.numberOfBed)

        assertEquals(dto.hasWifi, listing.hasWifi)
        assertEquals(dto.hasWashingMachine, listing.hasWashingMachine)
        assertEquals(dto.hasAirConditioning, listing.hasAirConditioning)
        assertEquals(dto.hasTv, listing.hasTv)
        assertEquals(dto.hasParking, listing.hasParking)

        assertEquals(dto.maxGuests, listing.maxGuests)

        assertEquals(dto.address, listing.address)
        assertEquals(dto.zipCode, listing.zipCode)
        assertEquals(dto.city, listing.city)
        assertEquals(dto.country, listing.country)

        assertEquals(dto.firstImage, listing.firstImage)
        assertEquals(dto.secondImage, listing.secondImage)
        assertEquals(dto.thirdImage, listing.thirdImage)

        assertEquals(dto.priceByNight, listing.priceByNight)
        assertEquals(dto.ownerId, listing.ownerId)
        assertEquals(dto.ownerName, listing.ownerName)
    }
}