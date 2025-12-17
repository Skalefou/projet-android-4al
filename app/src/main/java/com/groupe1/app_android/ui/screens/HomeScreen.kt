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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.domain.models.Listing
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.ui.components.FilterAdTriggerButton
import com.groupe1.app_android.ui.components.listing.ListingCard
import com.groupe1.app_android.viewModels.ListingsViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    currentUser: User,
    listingsViewModel: ListingsViewModel,
    onTriggerFilterAd: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    LaunchedEffect(currentUser.id) {
        listingsViewModel.getListingsFromRepo()
    }

    val remoteListings by listingsViewModel.remoteListings.collectAsState()

    Surface {
        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(12.dp)
        ) {
            Text(
                "Bonjour ${currentUser.firstName} !",
            )
            FilterAdTriggerButton(
                placeholder = "Commencer ma recherche",
                onClick = { onTriggerFilterAd() })
            Spacer(Modifier.padding(12.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                items(remoteListings) { listing ->
                    ListingCard(Modifier.clickable {
                        onItemClick(listing.id)
                    }, listing)
                }
            }
        }
    }
}