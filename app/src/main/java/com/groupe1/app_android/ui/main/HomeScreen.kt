package com.groupe1.app_android.ui.main

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.ui.components.ListingCard
import com.groupe1.app_android.ui.filterListing.components.FilterAdTriggerButton

@Composable
fun HomeScreen(
    onTriggerFilterAd: () -> Unit
) {
    val listings = listOf(
        Listing(
            id = 1L,
            title = "Charming Cottage",
            city = "Saint-Tropez",
            firstImage = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/120251269.jpg?k=638701338fd3475774a6d0e01848f44d44a450b162680bd7d9e7207e5aeb2871&o=",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
            ownerName = "Alice"
        ),
        Listing(
            id = 1L,
            title = "Charming Cottage",
            city = "Saint-Tropez",
            firstImage = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/243174535.jpg?k=a3fe8e2a3a120e71b15f8a8aa9c5efed87c16a87312e0cf606bb13879976b6f6&o=",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
            ownerName = "Alice"
        ),
        Listing(
            id = 1L,
            title = "Charming Cottage",
            city = "Saint-Tropez",
            firstImage = "https://a0.muscache.com/im/pictures/30060d42-b3ca-48e8-a279-a458ee8f958e.jpg",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
            ownerName = "Alice"
        ),
        Listing(
            id = 1L,
            title = "Charming Cottage",
            city = "Saint-Tropez",
            firstImage = "https://www.sothebysrealty-france.com/datas/biens/images/45650/diapo_medium/45650_00-2025-03-14-1618.jpg",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
            ownerName = "Alice"
        ),
        Listing(
            id = 1L,
            title = "Charming Cottage",
            city = "Saint-Tropez",
            firstImage = "https://media.vrbo.com/lodging/35000000/34990000/34984200/34984122/5c53ca02.jpg?impolicy=resizecrop&rw=575&rh=575&ra=fill",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
            ownerName = "Alice"
        ),
        Listing(
            id = 1L,
            title = "Charming Cottage",
            city = "Saint-Tropez",
            firstImage = "https://www.excellenceimmobilier.fr/public/img/big/Picture48894833jpg_60224a347e5bd.jpg",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
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
                    ListingCard(Modifier, listing)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onTriggerFilterAd = { println("Start filtering ad!") })
}