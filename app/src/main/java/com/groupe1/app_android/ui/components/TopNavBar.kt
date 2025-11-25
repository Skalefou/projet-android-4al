package com.groupe1.app_android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.groupe1.app_android.navigation.Routes

private data class NavAction(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val title = when (currentRoute) {
        Routes.home -> "Accueil"
        Routes.search, Routes.filterAd -> "Rechercher"
        Routes.settings -> "Paramètres"
        Routes.map -> "Carte"
        Routes.wishlist -> "Favoris"
        Routes.trips -> "Voyages"
        Routes.inbox -> "Messagerie"
        else -> ""
    }

    val navActions = listOf(
        NavAction(Routes.map, Icons.Default.Place, "Carte"),
        NavAction(Routes.wishlist, Icons.Default.FavoriteBorder, "Favoris"),
        NavAction(Routes.trips, Icons.Default.List, "Voyages"),
        NavAction(Routes.inbox, Icons.Default.Email, "Messagerie"),
        NavAction(Routes.profile, Icons.Default.AccountCircle, "Profil")
    )

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { onNavigate(Routes.home) }) {
                Icon(Icons.Default.Home, contentDescription = "Accueil")
            }
        },
        title = { Text(title) },
        actions = {
            navActions.forEach { action ->
                IconButton(onClick = { onNavigate(action.route) }) {
                    Icon(
                        imageVector = action.icon,
                        contentDescription = action.contentDescription
                    )
                }
            }
        }
    )
}
