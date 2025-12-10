package com.groupe1.app_android.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.groupe1.app_android.auth.UserPreferences
import com.groupe1.app_android.auth.isJwtValid
import com.groupe1.app_android.auth.userPreferencesDataStore
import com.groupe1.app_android.ui.components.TopNavBar
import com.groupe1.app_android.ui.main.InboxScreen
import com.groupe1.app_android.ui.main.MapScreen
import com.groupe1.app_android.ui.main.TripsScreen
import com.groupe1.app_android.ui.main.WishlistScreen
import com.groupe1.app_android.ui.main.components.RequireUser
import com.groupe1.app_android.ui.profile.ProfileRoute
import com.groupe1.app_android.ui.screens.CreateProposalScreen
import com.groupe1.app_android.ui.screens.HomeScreen
import com.groupe1.app_android.ui.screens.LoginRegisterGateScreen
import com.groupe1.app_android.ui.screens.LoginScreen
import com.groupe1.app_android.ui.screens.RegisterScreen
import com.groupe1.app_android.ui.screens.ListingScreen
import com.groupe1.app_android.ui.screens.filterListing.FilterWhereScreen
import com.groupe1.app_android.ui.settings.SettingsScreen
import com.groupe1.app_android.viewModels.ListingsViewModel


object Routes {
    const val GATE = "gate"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val SEARCH = "search"
    const val FILTER_LISTING = "filterListing"
    const val LISTING = "listing/{listingId}"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
    const val MAP = "map"
    const val WISHLIST = "wishlist"
    const val TRIPS = "trips"
    const val INBOX = "inbox"
    const val CREATE_PROPOSAL = "createProposal"
}

@Composable
fun AppNav(nav: NavHostController, listingsViewModel: ListingsViewModel) {
    val context = LocalContext.current

    val prefsState by context.userPreferencesDataStore
        .data
        .collectAsState(initial = null as UserPreferences?)

    val prefs = prefsState ?: return

    val hasValidRefresh = isJwtValid(prefs.refreshToken)

    val backStackEntry by nav.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val routesWithTopBar = setOf(
        Routes.HOME,
        Routes.SEARCH,
        Routes.FILTER_LISTING,
        Routes.SETTINGS,
        Routes.MAP,
        Routes.WISHLIST,
        Routes.TRIPS,
        Routes.INBOX
    )
    val showTopBar = currentRoute in routesWithTopBar

    Scaffold(
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
            startDestination = if (hasValidRefresh) Routes.HOME else Routes.GATE
        ) {
            composable(Routes.GATE) {
                LoginRegisterGateScreen(
                    onClickLogin = { nav.navigate(Routes.LOGIN) },
                    onClickRegister = { nav.navigate(Routes.REGISTER) })
            }
            composable(Routes.LOGIN) {
                LoginScreen(
                    onClickGoToHome = { nav.navigate(Routes.HOME) },
                    onClickGoToGate = { nav.navigate(Routes.GATE) }
                )
            }
            composable(Routes.REGISTER) {
                RegisterScreen(
                    onClickGoToHome = { nav.navigate(Routes.HOME) },
                    onClickGoToGate = { nav.navigate(Routes.GATE) },
                )
            }

            composable(Routes.HOME) {
                RequireUser(nav) { user ->
                    HomeScreen(
                        currentUser = user,
                        listingsViewModel,
                        onTriggerFilterAd = { nav.navigate(Routes.FILTER_LISTING) },
                        onItemClick = { listingId ->
                            nav.navigate("listing/$listingId")
                        },
                        onClickGoToCreateProposal = { nav.navigate(Routes.CREATE_PROPOSAL)}
                    )
                }
            }
            composable(Routes.FILTER_LISTING) {
                FilterWhereScreen()
            }
            composable(Routes.MAP) {
                MapScreen()
            }
            composable(Routes.WISHLIST) {
                WishlistScreen()
            }
            composable(Routes.TRIPS) {
                TripsScreen()
            }
            composable(Routes.INBOX) {
                InboxScreen()
            }
            composable(Routes.PROFILE) {
                ProfileRoute(nav = nav)
            }
            composable(Routes.SETTINGS) {
                SettingsScreen()
            }
            composable(
                route = Routes.LISTING,
                arguments = listOf(navArgument("listingId") { type = NavType.LongType }),
                enterTransition = {
                    fadeIn(tween(200)) + scaleIn(tween(200), initialScale = 0.9f)
                },
                exitTransition = {
                    fadeOut(tween(200)) + scaleOut(tween(200), targetScale = 1.1f)
                }) { backStackEntry ->
                val listingId = backStackEntry.arguments?.getLong("listingId")
                    ?: error("listingId missing in navigation")
                ListingScreen(
                    modifier = Modifier.background(Color.Gray),
                    listingId = listingId,
                    onBackClick = { nav.popBackStack() })
            }
            composable(Routes.CREATE_PROPOSAL) {
                RequireUser(nav) { user ->
                    CreateProposalScreen(
                        currentUser = user,
                        onBackClick = { nav.popBackStack() }
                    )
                }
            }
        }
    }
}