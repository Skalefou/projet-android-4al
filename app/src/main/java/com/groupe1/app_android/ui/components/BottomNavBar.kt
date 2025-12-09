package com.groupe1.app_android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.groupe1.app_android.ui.navigation.Routes

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem(Routes.HOME, Icons.Default.Home, "Accueil")
    object Search : BottomNavItem(Routes.SEARCH, Icons.Default.Search, "Rechercher")
    object Profile : BottomNavItem(Routes.PROFILE, Icons.Default.Person, "Profil")
    object Settings : BottomNavItem(Routes.SETTINGS, Icons.Default.Settings, "Paramètres")
}

@Composable
fun BottomNavBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) }
            )
        }
    }
}

@Composable
@Preview
fun BottomNavBarPreview() {
    BottomNavBar(currentRoute = Routes.HOME, onNavigate = {})
}
