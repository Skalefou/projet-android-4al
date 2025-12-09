package com.groupe1.app_android.ui.profile

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.dtos.UpdateUserDTO
import com.groupe1.app_android.models.User
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    initialUser: User = User(),
    onSave: (UpdateUserDTO) -> Unit = {},
    onLogout: () -> Unit = {},
    onBack: () -> Unit = {},
    uiMessage: String? = null,
    onMessageShown: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var firstName by rememberSaveable { mutableStateOf(initialUser.firstName) }
    var lastName by rememberSaveable { mutableStateOf(initialUser.lastName) }
    var email by rememberSaveable { mutableStateOf(initialUser.email) }
    var phone by rememberSaveable { mutableStateOf(initialUser.phone) }
    var city by rememberSaveable { mutableStateOf(initialUser.city) }
    var avatarUrl by rememberSaveable { mutableStateOf(initialUser.avatarUrl) }
    var isHost by rememberSaveable { mutableStateOf(initialUser.isHost) }
    var hostDescription by rememberSaveable { mutableStateOf(initialUser.hostDescription) }
    var languages by rememberSaveable { mutableStateOf(initialUser.languages) }
    var bio by rememberSaveable { mutableStateOf(initialUser.bio) }

    val isNameValid = firstName.isNotBlank()
    val isEmailValid = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val canSave = isNameValid && isEmailValid

    // show external message when provided
    LaunchedEffect(uiMessage) {
        uiMessage?.let {
            snackbarHostState.showSnackbar(it)
            onMessageShown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profil") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = { onLogout() }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Se déconnecter")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("Prénom") },
                    isError = !isNameValid && firstName.isNotEmpty(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Nom") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Téléphone") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Ville") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = languages,
                    onValueChange = { languages = it },
                    label = { Text("Langues") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = avatarUrl,
                onValueChange = { avatarUrl = it },
                label = { Text("Avatar URL") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Hôte" )
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = isHost, onCheckedChange = { isHost = it })
            }

            if (isHost) {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = hostDescription,
                    onValueChange = { hostDescription = it },
                    label = { Text("Description hôte") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        if (canSave) {
                            val update = UpdateUserDTO(
                                firstName = firstName.trim(),
                                lastName = lastName.trim(),
                                email = email.trim(),
                                phone = phone.trim(),
                                avatarUrl = avatarUrl.trim(),
                                city = city.trim(),
                                isHost = isHost,
                                hostDescription = hostDescription.trim(),
                                languages = languages.trim(),
                                bio = bio.trim()
                            )
                            onSave(update)
                        } else {
                            coroutineScope.launch {
                                val msg = when {
                                    !isNameValid -> "Le prénom est requis"
                                    email.isBlank() -> "L'email est requis"
                                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email invalide"
                                    else -> "Informations invalides"
                                }
                                snackbarHostState.showSnackbar(msg)
                            }
                        }
                    }
                ) {
                    Text("Enregistrer")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(
            initialUser = User(
                id = 1,
                firstName = "Alice",
                lastName = "Dupont",
                email = "alice@example.com",
                phone = "+33123456789",
                city = "Paris",
                isHost = true,
                hostDescription = "J'aime accueillir des voyageurs",
                languages = "FR,EN",
                bio = "Développeuse Android — aime le Jetpack Compose"
            ),
            onSave = {},
            onLogout = {},
            onBack = {},
            uiMessage = null
        )
    }
}
