package com.groupe1.app_android.ui.loginRegister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.dtos.LoginUserDTO
import com.groupe1.app_android.services.UserService
import com.groupe1.app_android.session.UserStore
import com.groupe1.app_android.ui.theme.defaultOutlinedTextFieldColors

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailErrorEmpty by remember { mutableStateOf(false) }
    var passwordErrorEmpty by remember { mutableStateOf(false) }
    var connexionError by remember { mutableStateOf(false) }

    suspend fun loginPost() {
        val loginUser = LoginUserDTO(
            email = email,
            password = password
        )
        val response = UserService.loginUser(loginUser)
        UserStore.login(response.user)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Connexion",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 32.dp),
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it; connexionError = false},
            label = { Text("Email") },
            isError = connexionError || emailErrorEmpty,
            modifier = Modifier.padding(bottom = 16.dp),
            colors = defaultOutlinedTextFieldColors(),
            singleLine = true
        )
        if (emailErrorEmpty) {
            Text(
                text = "Le champ \"email\" est obligatoire.",
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it; connexionError = false },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            isError = connexionError || passwordErrorEmpty,
            modifier = Modifier.padding(bottom = 16.dp),
            colors = defaultOutlinedTextFieldColors(),
            singleLine = true
        )
        if (passwordErrorEmpty) {
            Text(
                text = "Le champ \"mot de passe\" est obligatoire.",
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        if (connexionError) {
            Text(
                text = "Email ou mot de passe incorrect",
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
        }

        Button(
            onClick= {
                emailErrorEmpty = email.isBlank()
                passwordErrorEmpty = password.isBlank()
                loginPost()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Se connecter")
        }
    }
}