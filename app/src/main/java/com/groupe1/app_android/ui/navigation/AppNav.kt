package com.groupe1.app_android.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.groupe1.app_android.auth.AuthState
import com.groupe1.app_android.ui.screens.HomeScreen
import com.groupe1.app_android.ui.screens.LoginRegisterGateScreen
import com.groupe1.app_android.ui.screens.LoginScreen
import com.groupe1.app_android.ui.screens.RegisterScreen
import com.groupe1.app_android.ui.screens.ListingScreen
import com.groupe1.app_android.ui.screens.filterListing.FilterWhereScreen
import com.groupe1.app_android.viewModels.ListingsViewModel

object Routes {
    const val GATE = "gate"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val FILTER_AD = "filterAd"
    const val LISTING = "listing/{listingId}"
}

@Composable
fun AppNav(nav: NavHostController, listingsViewModel: ListingsViewModel) {
    val isLoggedIn by AuthState.isLoggedIn.collectAsState()

    NavHost(
        navController = nav,
        startDestination = if (isLoggedIn) Routes.HOME else Routes.GATE
    ) {
        composable(Routes.GATE) {
            LoginRegisterGateScreen(
                onClickLogin = { nav.navigate(Routes.LOGIN) },
                onClickRegister = { nav.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.LOGIN) { LoginScreen() }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onClickGoToHome = { nav.navigate(Routes.HOME) }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                listingsViewModel,
                onTriggerFilterAd = { nav.navigate(Routes.FILTER_AD) },
                onItemClick = { listingId ->
                    nav.navigate("listing/$listingId")
                }
            )
        }
        composable(Routes.FILTER_AD) {
            FilterWhereScreen()
        }
        composable(
            route = Routes.LISTING,
            arguments = listOf(navArgument("listingId") { type = NavType.LongType })
        ) { backStackEntry ->
            val listingId = backStackEntry.arguments?.getLong("listingId")
            requireNotNull(listingId)
            ListingScreen(
                modifier = Modifier
                    .background(Color.Gray),
                listingId = listingId)
        }
    }
}