package com.groupe1.app_android.ui.main.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.groupe1.app_android.auth.UserPreferences
import com.groupe1.app_android.auth.userPreferencesDataStore
import com.groupe1.app_android.models.User
import com.groupe1.app_android.navigation.Routes

@Composable
fun RequireUser(
    navController: NavHostController,
    content: @Composable (User) -> Unit
) {
    val context = LocalContext.current

    val prefsState by context.userPreferencesDataStore
        .data
        .collectAsState(initial = null)

    val prefs = prefsState

    if (prefs == null) {
        return
    }

    val user = prefs.currentUser

    LaunchedEffect(user) {
        if (user == null) {
            navController.navigate(Routes.login) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    if (user != null) {
        content(user)
    }
}
