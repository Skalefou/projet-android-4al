package com.groupe1.app_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupe1.app_android.auth.AuthState
import com.groupe1.app_android.ui.loginRegister.LoginRegisterGateScreen
import com.groupe1.app_android.ui.loginRegister.LoginScreen
import com.groupe1.app_android.ui.loginRegister.RegisterScreen

object Routes {
    const val gate = "gate"
    const val login = "login"
    const val register = "register"
    const val home = "home"
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
        composable(Routes.login) { LoginScreen() }
        composable(Routes.register) { RegisterScreen() }

        composable(Routes.home) {
            // TODO: écran d’accueil une fois connecté
        }
    }
}