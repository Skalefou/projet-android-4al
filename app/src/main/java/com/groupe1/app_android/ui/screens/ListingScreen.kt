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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.groupe1.app_android.R
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.ui.components.shared.RoundIconButton

@Composable
fun ListingScreen(modifier: Modifier = Modifier, listingId: Long, onBackClick: () -> Unit) {
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
                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RoundIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = onBackClick,
                )
                RoundIconButton(
                    icon = Icons.Default.FavoriteBorder,
                    onClick = {},
                )
            }
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    listing.title,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    listing.city + ", " +
                            listing.country + " • " +
                            listing.maxGuests + " voyageurs • " +
                            listing.numberOfBed + " lits • " +
                            listing.numberOfBathrooms + " salle de bain • " +
                            listing.numberOfRooms + " chambres",
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider()
                Text(
                    listing.description,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun ListingScreenPreview() {
    ListingScreen(Modifier, 1L, onBackClick = {})
}