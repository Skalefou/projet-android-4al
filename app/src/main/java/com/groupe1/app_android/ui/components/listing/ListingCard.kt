package com.groupe1.app_android.ui.components.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.groupe1.app_android.R
import com.groupe1.app_android.domain.models.Listing

@Composable
fun ListingCard(modifier: Modifier, listing: Listing) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        AsyncImage(
            model = listing.firstImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .aspectRatio(16f / 9f)
                .fillMaxWidth()
                .background(Color.Blue)
        )
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                listing.title + " • " + listing.city,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
            Text(
                listing.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = Color.Gray
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(listing.priceByNight.toString() + "€")
                    }

                    append(" la nuit")
                },
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            )
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
}