package com.groupe1.app_android.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.groupe1.app_android.auth.userPreferencesDataStore
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.ui.navigation.Routes

@Composable
fun RequireUser(
    navController: NavHostController,
    content: @Composable (User) -> Unit
) {
    val context = LocalContext.current

    val prefsState by context.userPreferencesDataStore
        .data
        .collectAsState(initial = null)

    val prefs = prefsState ?: return

    val user = prefs.currentUser

    LaunchedEffect(user) {
        if (user == null) {
            navController.navigate(Routes.LOGIN) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    if (user != null) {
        content(user)
    }
}
