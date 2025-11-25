package com.groupe1.app_android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.groupe1.app_android.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    currentRoute: String?,
    onOpenProfile: () -> Unit
) {
    val title = when (currentRoute) {
        Routes.home -> "Accueil"
        Routes.search, Routes.filterAd -> "Rechercher"
        Routes.settings -> "Paramètres"
        else -> ""
    }

    CenterAlignedTopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = onOpenProfile) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Profil")
            }
        }
    )
}
