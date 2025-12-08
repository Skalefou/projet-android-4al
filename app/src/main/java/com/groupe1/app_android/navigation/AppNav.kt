package com.groupe1.app_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupe1.app_android.auth.AuthState
import com.groupe1.app_android.ui.main.HomeScreen
import com.groupe1.app_android.ui.loginRegister.LoginRegisterGateScreen
import com.groupe1.app_android.ui.loginRegister.LoginScreen
import com.groupe1.app_android.ui.loginRegister.RegisterScreen
import com.groupe1.app_android.ui.main.FilterAdScreen
import com.groupe1.app_android.ui.main.components.RequireUser

object Routes {
    const val gate = "gate"
    const val login = "login"
    const val register = "register"
    const val home = "home"
    const val filterAd = "filterAd"
}

@Composable
fun AppNav(nav: NavHostController) {
    val isLoggedIn by AuthState.isLoggedIn.collectAsState()

    NavHost(
        navController = nav,
        startDestination = if (isLoggedIn) Routes.home else Routes.gate
    ) {
        composable(Routes.gate) {
            LoginRegisterGateScreen(
                onClickLogin = { nav.navigate(Routes.login) },
                onClickRegister = { nav.navigate(Routes.register) }
            )
        }
        composable(Routes.login) { LoginScreen(
            onClickGoToHome={ nav.navigate(Routes.home) }
        ) }
        composable(Routes.register) { RegisterScreen(
            onClickGoToHome={ nav.navigate(Routes.home)
            }
        ) }

        composable(Routes.home) {
            RequireUser(nav) { user ->
                HomeScreen(
                    currentUser = user,
                    onTriggerFilterAd = { nav.navigate(Routes.filterAd) }
                )
            }
        }
        composable(Routes.filterAd) {
            FilterAdScreen()
        }
    }
}