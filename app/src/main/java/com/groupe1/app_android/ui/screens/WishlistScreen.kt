package com.groupe1.app_android.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.ui.components.listing.ListingCard
import com.groupe1.app_android.viewModels.FavoritesViewModel

@Composable
fun WishlistScreen(
    modifier: Modifier,
    favoritesViewModel: FavoritesViewModel,
    currentUser: User,
    onItemClick: (Long) -> Unit
) {
    val favorites by favoritesViewModel.remoteFavorites.collectAsState()
    val error by favoritesViewModel.error.collectAsState()

    LaunchedEffect(currentUser.id) {
        favoritesViewModel.getFavorites()
        Log.d("WishlistScreen", "Favorites: $favorites")
    }

    Surface(
        modifier
            .background(Color.White)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(12.dp)
    ) {
        if (error != null) Text(error!!)
        else if (favorites.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center) {
                Text("Aucun favori")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                items(favorites) { listing ->
                    ListingCard(Modifier.clickable {
                        onItemClick(listing.id)
                    }, listing)
                }
            }
        }
    }
}
