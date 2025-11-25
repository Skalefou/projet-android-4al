package com.groupe1.app_android.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.groupe1.app_android.auth.AuthState
import com.groupe1.app_android.ui.loginRegister.LoginRegisterGateScreen
import com.groupe1.app_android.ui.loginRegister.LoginScreen
import com.groupe1.app_android.ui.loginRegister.RegisterScreen
import com.groupe1.app_android.ui.main.FilterAdScreen
import com.groupe1.app_android.ui.main.HomeScreen
import com.groupe1.app_android.ui.components.TopNavBar
import com.groupe1.app_android.ui.main.MapScreen
import com.groupe1.app_android.ui.main.WishlistScreen
import com.groupe1.app_android.ui.main.TripsScreen
import com.groupe1.app_android.ui.main.InboxScreen

object Routes {
    const val gate = "gate"
    const val login = "login"
    const val register = "register"
    const val home = "home"
    const val search = "search" // alias pour FilterAdScreen
    const val filterAd = "filterAd" // compat éventuelle
    const val profile = "profile"
    const val settings = "settings"
    const val map = "map"
    const val wishlist = "wishlist"
    const val trips = "trips"
    const val inbox = "inbox"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav(nav: NavHostController) {
    val isLoggedIn by AuthState.isLoggedIn.collectAsState()
    val backStackEntry by nav.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val routesWithTopBar = setOf(
        Routes.home,
        Routes.search,
        Routes.filterAd,
        Routes.settings,
        Routes.map,
        Routes.wishlist,
        Routes.trips,
        Routes.inbox
    )
    val showTopBar = currentRoute in routesWithTopBar

    androidx.compose.material3.Scaffold(
        topBar = {
            if (showTopBar) {
                TopNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { target ->
                        if (target != null && target != currentRoute) {
                            nav.navigate(target) { launchSingleTop = true }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = nav,
            startDestination = if (isLoggedIn) Routes.home else Routes.gate
        ) {
            composable(Routes.gate) {
                LoginRegisterGateScreen(
                    onClickLogin = { nav.navigate(Routes.login) },
                    onClickRegister = { nav.navigate(Routes.register) }
                )
            }
            composable(Routes.login) { LoginScreen() }
            composable(Routes.register) {
                RegisterScreen(
                    onClickGoToHome = { nav.navigate(Routes.home) }
                )
            }
            composable(Routes.home) { HomeScreen(onTriggerFilterAd = { nav.navigate(Routes.search) }) }
            composable(Routes.search) { FilterAdScreen() }
            composable(Routes.filterAd) { FilterAdScreen() }
            composable(Routes.map) { MapScreen() }
            composable(Routes.wishlist) { WishlistScreen() }
            composable(Routes.trips) { TripsScreen() }
            composable(Routes.inbox) { InboxScreen() }
            composable(Routes.profile) { com.groupe1.app_android.ui.profile.ProfileRoute(nav = nav) }
            composable(Routes.settings) { com.groupe1.app_android.ui.settings.SettingsScreen() }
        }
    }
}