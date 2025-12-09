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
import com.groupe1.app_android.ui.navigation.Routes

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
        Routes.HOME -> "Accueil"
        Routes.SEARCH, Routes.FILTER_LISTING -> "Rechercher"
        Routes.SETTINGS -> "Paramètres"
        Routes.MAP -> "Carte"
        Routes.WISHLIST -> "Favoris"
        Routes.TRIPS -> "Voyages"
        Routes.INBOX -> "Messagerie"
        else -> ""
    }

    val navActions = listOf(
        NavAction(Routes.MAP, Icons.Default.Place, "Carte"),
        NavAction(Routes.WISHLIST, Icons.Default.FavoriteBorder, "Favoris"),
        NavAction(Routes.TRIPS, Icons.Default.List, "Voyages"),
        NavAction(Routes.INBOX, Icons.Default.Email, "Messagerie"),
        NavAction(Routes.PROFILE, Icons.Default.AccountCircle, "Profil")
    )

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { onNavigate(Routes.HOME) }) {
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
