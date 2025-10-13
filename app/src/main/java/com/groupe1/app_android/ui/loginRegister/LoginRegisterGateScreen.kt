package com.groupe1.app_android.ui.loginRegister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginRegisterGateScreen(
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenue")
            Text("Pour continuer, créez un compte ou connectez-vous.")

            Button(
                onClick = onClickRegister,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("S’inscrire")
            }

            OutlinedButton(
                onClick = onClickLogin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Se connecter")
            }
        }
    }
}