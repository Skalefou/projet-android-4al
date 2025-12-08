package com.groupe1.app_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupe1.app_android.auth.UserPreferences
import com.groupe1.app_android.auth.isJwtValid
import com.groupe1.app_android.ui.main.HomeScreen
import com.groupe1.app_android.ui.loginRegister.LoginRegisterGateScreen
import com.groupe1.app_android.ui.loginRegister.LoginScreen
import com.groupe1.app_android.ui.loginRegister.RegisterScreen
import com.groupe1.app_android.ui.main.FilterAdScreen
import com.groupe1.app_android.ui.main.components.RequireUser
import com.groupe1.app_android.auth.userPreferencesDataStore

object Routes {
    const val GATE = "gate"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val FILTER_AD = "filterAd"
}

@Composable
fun AppNav(nav: NavHostController) {
    val context = LocalContext.current

    val prefsState by context.userPreferencesDataStore
        .data
        .collectAsState(initial = null as UserPreferences?)

    val prefs = prefsState

    if (prefs == null) {
        return
    }

    val hasValidRefresh = isJwtValid(prefs.refreshToken)

    NavHost(
        navController = nav,
        startDestination = if (hasValidRefresh) Routes.HOME else Routes.GATE
    ) {
        composable(Routes.GATE) {
            LoginRegisterGateScreen(
                onClickLogin = { nav.navigate(Routes.LOGIN) },
                onClickRegister = { nav.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.LOGIN) { LoginScreen(
            onClickGoToHome={ nav.navigate(Routes.HOME) }
        ) }
        composable(Routes.REGISTER) { RegisterScreen(
            onClickGoToHome={ nav.navigate(Routes.HOME)
            }
        ) }

        composable(Routes.HOME) {
            RequireUser(nav) { user ->
                HomeScreen(
                    currentUser = user,
                    onTriggerFilterAd = { nav.navigate(Routes.FILTER_AD) }
                )
            }
        }
        composable(Routes.FILTER_AD) {
            FilterAdScreen()
        }
    }
}