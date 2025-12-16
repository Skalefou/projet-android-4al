package com.groupe1.app_android.ui.screens.filterListing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.R
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.ui.components.ListingPreviewCard
import com.groupe1.app_android.ui.components.shared.RoundIconButton
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.groupe1.app_android.viewModels.FiltersViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun FilterResultsScreen(
    viewModel: FiltersViewModel,
    onListingClick: (Listing) -> Unit,
    onBack: () -> Unit,
    onEditFilter: () -> Unit
) {
    var listings by remember { mutableStateOf<List<Listing>>(emptyList()) }
    LaunchedEffect(Unit) {
        listings = viewModel.getListingsFiltered()
    }

    val filters by viewModel.filters.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(25.dp, 50.dp, 25.dp, 50.dp)
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = onBack
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .height(40.dp)
                        .background(
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(50)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${filters.city} · Logements",
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 14.sp
                    )
                }

                RoundIconButton(
                    icon = Icons.Default.Tune,
                    onClick = onEditFilter
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(listings, key = { it.id }) { listing ->
                    ListingPreviewCard(
                        listing = listing,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onListingClick(listing) }
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ListingResultsScreenPreview() {
//    val fakeListing = remember {
//        Listing(
//            id = 1L,
//            title = "Chambre 2 avec vue sur le balcon",
//            description = "",
//            numberOfRooms = 2,
//            numberOfBathrooms = 1,
//            numberOfBed = 2,
//            hasWifi = true,
//            hasWashingMachine = true,
//            hasAirConditioning = false,
//            hasTv = true,
//            hasParking = false,
//            maxGuests = 3,
//            address = "Centro",
//            zipCode = "28001",
//            city = "Madrid",
//            country = "Espagne",
//            firstImage = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/120251269.jpg?k=638701338fd3475774a6d0e01848f44d44a450b162680bd7d9e7207e5aeb2871&o=",
//            secondImage = "",
//            thirdImage = "",
//            priceByNight = 205,
//            ownerId = 1,
//            ownerName = "Lucia"
//        )
//    }
//
//    FilterResultsScreen(
//        listings = List(4) { fakeListing.copy(id = it.toLong()) },
//        onListingClick = {},
//        onBack = {},
//        onEditFilter = {},
//        viewModel = FiltersViewModel()
//    )
//}