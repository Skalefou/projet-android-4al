package com.groupe1.app_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.groupe1.app_android.domain.models.Listing

@Composable
fun ListingCard(modifier: Modifier, listing: Listing) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AsyncImage(
            model = "https://media.istockphoto.com/id/627795510/photo/example.jpg?s=612x612&w=0&k=20&c=lpUf5rjPVd6Kl_M6heqC8sUncR4FLmtsRzeYdTr5X_I=",
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(250.dp)
                .fillMaxWidth()
                .background(Color.Blue)
        )
        Text(
            "Appartement ",
            style = MaterialTheme.typography.displayMedium
        )

        Column {
            Text("Teeest")
            Text("GTGGTGG")
        }
    }
}

@Preview
@Composable
fun ListingCardPreview() {
    ListingCard(
        Modifier,
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