package com.groupe1.app_android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.LocalLaundryService
import androidx.compose.material.icons.outlined.LocalParking
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.groupe1.app_android.R
import com.groupe1.app_android.ui.components.listing.ListingBenefit
import com.groupe1.app_android.ui.components.shared.RoundIconButton
import com.groupe1.app_android.ui.theme.HoneyYellow
import com.groupe1.app_android.viewModels.ListingViewModel
import com.groupe1.app_android.viewModels.ListingsViewModel

@Composable
fun ListingScreen(modifier: Modifier = Modifier, listingId: Long, onBackClick: () -> Unit, listingViewModel: ListingViewModel) {
    val listing by listingViewModel.remoteListing.collectAsState()
    val remoteIsFavorite by listingViewModel.remoteIsFavorite.collectAsState()

    if (listing == null) {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Erreur de chargement des données.…")
            }
        }
        return
    }

    val l = listing!!

    Scaffold(
        bottomBar = {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(0.5.dp, Color.LightGray)
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HoneyYellow
                )
            ) {
                Text("Réserver")
            }
        }
    ) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy((-30).dp)
        ) {
            Box {
                AsyncImage(
                    model = l.firstImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.image_placeholder),
                    modifier = Modifier
                        .aspectRatio(16f / 14f)
                        .fillMaxWidth()
                        .background(Color(0xC5C5C5))
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
                        icon = if(remoteIsFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        onClick = {
                            listingViewModel.likeListing()
                          },
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
                        .background(Color.White)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        l.title,
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        l.city + ", " +
                                l.country + " • " +
                                l.maxGuests + " voyageurs • " +
                                l.numberOfBed + " lits • " +
                                l.numberOfBathrooms + " salle de bain • " +
                                l.numberOfRooms + " chambres",
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp
                    )
                    HorizontalDivider(thickness = 0.5.dp)
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        AsyncImage(
                            model = "https://api.dicebear.com/9.x/lorelei/png?seed=${l.ownerName}",
                            contentDescription = "Owner avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(48.dp)
                        )
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                l.ownerName,
                                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                            Text(
                                "Hôte",
                                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                    HorizontalDivider(thickness = 0.5.dp)
                    Text(
                        l.description,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontSize = 16.sp,
                    )
                    HorizontalDivider(thickness = 0.5.dp)
                    Text(
                        "Ce que propose ce logement",
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    if (l.hasWifi) {
                        ListingBenefit(Icons.Outlined.Wifi, "Wifi")
                    }
                    if (l.hasWashingMachine) {
                        ListingBenefit(Icons.Outlined.LocalLaundryService, "Machine à laver")
                    }
                    if (l.hasAirConditioning) {
                        ListingBenefit(Icons.Outlined.AcUnit, "Climatisation")
                    }
                    if (l.hasTv) {
                        ListingBenefit(Icons.Outlined.Tv, "Télévision")
                    }
                    if (l.hasParking) {
                        ListingBenefit(Icons.Outlined.LocalParking, "Parking")
                    }
                }
            }
        }
    }
}