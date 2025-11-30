package com.groupe1.app_android.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.ui.components.ListingCard
import com.groupe1.app_android.ui.components.FilterAdTriggerButton
import com.groupe1.app_android.viewModels.ListingsViewModel

@Composable
fun HomeScreen(
    listingsViewModel: ListingsViewModel,
    onTriggerFilterAd: () -> Unit
) {
    val remoteListings by listingsViewModel.remoteListings.collectAsState()

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
                items(remoteListings) { listing ->
                    ListingCard(Modifier, listing)
                }
            }
        }
    }
}