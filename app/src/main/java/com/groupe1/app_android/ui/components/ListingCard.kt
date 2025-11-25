package com.groupe1.app_android.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.groupe1.app_android.domain.models.Listing

@Composable
fun ListingCard(listing: Listing) {
}

@Preview
@Composable
fun ListingCardPreview() {
    ListingCard(
        Listing(
            id = 1L,
            title = "Charming Cottage",
            description = "A cozy cottage in the countryside.",
            priceByNight = 120.0,
            ownerId = 10L,
            ownerName = "Alice"
        )
    )
}