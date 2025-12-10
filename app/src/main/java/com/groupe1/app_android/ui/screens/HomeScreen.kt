package com.groupe1.app_android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.ui.components.AddProposalButton
import com.groupe1.app_android.ui.components.FilterAdTriggerButton
import com.groupe1.app_android.ui.components.listing.ListingCard
import com.groupe1.app_android.viewModels.ListingsViewModel

@Composable
fun HomeScreen(
    currentUser: User,
    listingsViewModel: ListingsViewModel,
    onTriggerFilterAd: () -> Unit,
    onItemClick: (Long) -> Unit,
    onClickGoToCreateProposal: () -> Unit
) {
    // TODO: USE ME
    val remoteListings by listingsViewModel.remoteListings.collectAsState()

    // Todo : remove, it's temporary
    val listings = listOf(
        Listing(
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
        ),
        Listing(
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
        ),
        Listing(
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
        ),
        Listing(
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
        ),
        Listing(
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
        ),
        Listing(
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
    )

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(12.dp)
        ) {
            FilterAdTriggerButton(
                placeholder = "Commencer ma recherche",
                onClick = { onTriggerFilterAd() })
            Spacer(Modifier.padding(12.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                items(listings) { listing ->
                    ListingCard(Modifier.clickable {
                        onItemClick(listing.id)
                    }, listing)
                }
            }
        }
        AddProposalButton(
            onClick = { onClickGoToCreateProposal() }
        )
    }
}