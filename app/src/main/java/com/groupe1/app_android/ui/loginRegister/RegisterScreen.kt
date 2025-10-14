package com.groupe1.app_android.ui.loginRegister

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.theme.HoneyYellow


@Composable
fun RegisterScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var firstNameError by remember { mutableStateOf(false) }
    var lastNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var passwordMismatchError by remember { mutableStateOf(false) }
    var validEmailError by remember { mutableStateOf(false) }
    var validPasswordError by remember { mutableStateOf(false) }

    val colorOutlinedTextField = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = HoneyYellow,
        unfocusedBorderColor = HoneyYellow,
        errorBorderColor = Color.Red
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Inscription", fontSize = 32.sp, modifier = Modifier.padding(bottom = 32.dp), fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it; firstNameError = false },
            label = { Text("Prénom") },
            isError = firstNameError,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            colors = colorOutlinedTextField
        )
        if (firstNameError) {
            Text("Le prénom est requis", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it; lastNameError = false },
            label = { Text("Nom") },
            isError = lastNameError,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            colors = colorOutlinedTextField
        )
        if (lastNameError) {
            Text("Le nom est requis", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = false },
            label = { Text("Email") },
            isError = emailError,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            colors = colorOutlinedTextField
        )
        if (emailError) {
            Text("L'email est requis", color = Color.Red, fontSize = 12.sp)
        }
        if (validEmailError) {
            Text("L'email n'est pas valide", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = false },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            colors = colorOutlinedTextField
        )
        if (passwordError) {
            Text("Le mot de passe est requis", color = Color.Red, fontSize = 12.sp)
        }
        if (validPasswordError) {
            Text("Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule et un chiffre", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it; confirmPasswordError = false },
            label = { Text("Confirmation du mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = colorOutlinedTextField
        )
        if (confirmPasswordError) {
            Text("La confirmation est requise", color = Color.Red, fontSize = 12.sp)
        }
        if (passwordMismatchError) {
            Text("Les mots de passe ne correspondent pas", color = Color.Red, fontSize = 12.sp)
        }

        Button(
            onClick = {
                firstNameError = firstName.isBlank()
                lastNameError = lastName.isBlank()
                emailError = email.isBlank()
                passwordError = password.isBlank()
                confirmPasswordError = confirmPassword.isBlank()
                passwordMismatchError = password != confirmPassword
                validEmailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()
                validPasswordError = !Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$").matches(password)  && password.isNotBlank()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("S'inscrire")
        }
    }
}