package com.groupe1.app_android.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ProfileRoute(
    nav: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val uiMessage by viewModel.uiMessage.collectAsState()

    // load on first composition
    LaunchedEffect(Unit) {
        viewModel.loadUser()
    }

    if (loading && user == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    MaterialTheme {
        ProfileScreen(
            initialUser = user ?: com.groupe1.app_android.models.User(),
            onSave = { update -> viewModel.updateUser(update) },
            onLogout = {
                viewModel.logout()
                nav.navigate(com.groupe1.app_android.navigation.Routes.gate) {
                    popUpTo(com.groupe1.app_android.navigation.Routes.home) { inclusive = true }
                }
            },
            onBack = { nav.popBackStack() },
            uiMessage = uiMessage,
            onMessageShown = { viewModel.clearMessage() }
        )
    }
}
