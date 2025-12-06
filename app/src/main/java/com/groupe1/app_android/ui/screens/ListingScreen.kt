package com.groupe1.app_android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.ui.components.shared.RoundIconButton

@Composable
fun ListingScreen(modifier: Modifier = Modifier, listingId: Long) {
    val listing = Listing(
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

    Column(modifier, verticalArrangement = Arrangement.spacedBy((-16).dp)) {
        Box {
            AsyncImage(
                model = listing.firstImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(16f / 13f)
                    .fillMaxWidth()
                    .background(Color.Blue)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RoundIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = {},
                )
                RoundIconButton(
                    icon = Icons.Default.FavoriteBorder,
                    onClick = {},
                )
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    "Listing Screen"
                )
            }
        }
    }
}

@Preview
@Composable
fun ListingScreenPreview() {
    ListingScreen(Modifier, 1L)
}