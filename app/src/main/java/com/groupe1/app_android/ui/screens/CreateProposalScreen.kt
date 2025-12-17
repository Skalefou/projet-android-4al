package com.groupe1.app_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.data.remote.models.CreateProposalDTO
import com.groupe1.app_android.data.repository.ListingRepositoryImpl
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.domain.usecase.listings.CreateProposalUseCase
import com.groupe1.app_android.domain.usecase.listings.GetAllListingUseCase
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import com.groupe1.app_android.ui.components.BackButton
import com.groupe1.app_android.ui.components.shared.BoolSwitchField
import com.groupe1.app_android.ui.components.shared.FormTextField
import com.groupe1.app_android.ui.components.shared.IntField
import kotlinx.coroutines.launch

@Composable
fun CreateProposalScreen(
    currentUser: User,
    onBackClick: () -> Unit
) {
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }

    var numberOfRooms by remember { mutableIntStateOf(1) }
    var numberOfBathrooms by remember { mutableIntStateOf(1) }
    var numberOfBed by remember { mutableIntStateOf(1) }
    var maxGuest by remember { mutableIntStateOf(2) }
    var priceByNight by remember { mutableIntStateOf(100) }

    var hasWifi by remember { mutableStateOf(false) }
    var hasWashingMachine by remember { mutableStateOf(false) }
    var hasAirConditioning by remember { mutableStateOf(false) }
    var hasTv by remember { mutableStateOf(false) }
    var hasParking by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var typeError by remember { mutableStateOf(false) }

    var numberOfRoomsError by remember { mutableStateOf(false) }
    var numberOfBathroomsError by remember { mutableStateOf(false) }
    var numberOfBedError by remember { mutableStateOf(false) }
    var maxGuestError by remember { mutableStateOf(false) }
    var priceByNightError by remember { mutableStateOf(false) }

    var hasWifiError by remember { mutableStateOf(false) }
    var hasWashingMachineError by remember { mutableStateOf(false) }
    var hasAirConditioningError by remember { mutableStateOf(false) }
    var hasTvError by remember { mutableStateOf(false) }
    var hasParkingError by remember { mutableStateOf(false) }

    var addressError by remember { mutableStateOf(false) }
    var zipCodeError by remember { mutableStateOf(false) }
    var cityError by remember { mutableStateOf(false) }
    var countryError by remember { mutableStateOf(false) }

    var connexionError by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val listingRepository = ListingRepositoryImpl()
    val listingUseCases = ListingUseCases(
        GetAllListingUseCase(listingRepository),
        CreateProposalUseCase(listingRepository)
    )


    suspend fun createProposalScreenPost(): Boolean {
        return try {
            val createProposal = CreateProposalDTO(
                title = title,
                description = description,
                type = type,
                numberOfRooms = numberOfRooms,
                numberOfBathrooms = numberOfBathrooms,
                numberOfBed = numberOfBed,
                hasWifi = hasWifi,
                hasWashingMachine = hasWashingMachine,
                hasAirConditioning = hasAirConditioning,
                hasTv = hasTv,
                hasParking = hasParking,
                maxGuests = maxGuest,
                address = address,
                zipCode = zipCode,
                city = city,
                country = country,
                priceByNight = priceByNight
            )
            val response = listingUseCases.createProposal.invoke(
                createProposal
            )
            connexionError = false
            true
        } catch (e: Exception) {
            connexionError = true
            false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState)
    ) {
        BackButton(
            modifier = Modifier.align(Alignment.Start),
            onClick = { onBackClick() }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Création d'une annonce",
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 32.dp),
                fontWeight = FontWeight.Bold
            )

            FormTextField(
                value = title,
                onValueChange = { title = it; titleError = false },
                label = "Nom de l'annonce",
                error = titleError,
                errorText = "Un nom d'annonce est requis, max 200 caractères",
                singleLine = true
            )

            FormTextField(
                value = description,
                onValueChange = { description = it; descriptionError = false },
                label = "Description",
                error = descriptionError,
                errorText = "Une description est requise, max 1000 caractères",
                singleLine = false
            )

            FormTextField(
                value = type,
                onValueChange = { type = it; typeError = false },
                label = "Type",
                error = typeError,
                errorText = "Le type est requis",
                singleLine = true
            )

            IntField(
                label = "Nombre de pièces",
                value = numberOfRooms,
                onValueChange = { numberOfRooms = it; numberOfRoomsError = false },
                min = 1,
                max = 50,
                error = numberOfRoomsError,
                errorText = "Valeur invalide (min 1)"
            )

            IntField(
                label = "Nombre de salles de bain",
                value = numberOfBathrooms,
                onValueChange = { numberOfBathrooms = it; numberOfBathroomsError = false },
                min = 1,
                max = 20,
                error = numberOfBathroomsError,
                errorText = "Valeur invalide (min 1)"
            )

            IntField(
                label = "Nombre de lits",
                value = numberOfBed,
                onValueChange = { numberOfBed = it; numberOfBedError = false },
                min = 1,
                max = 50,
                error = numberOfBedError,
                errorText = "Valeur invalide (min 1)"
            )

            BoolSwitchField(
                label = "Wifi",
                checked = hasWifi,
                onCheckedChange = { hasWifi = it; hasWifiError = false },
                error = hasWifiError,
                errorText = "Champ invalide"
            )

            BoolSwitchField(
                label = "Machine à laver",
                checked = hasWashingMachine,
                onCheckedChange = { hasWashingMachine = it; hasWashingMachineError = false },
                error = hasWashingMachineError,
                errorText = "Champ invalide"
            )

            BoolSwitchField(
                label = "Climatisation",
                checked = hasAirConditioning,
                onCheckedChange = { hasAirConditioning = it; hasAirConditioningError = false },
                error = hasAirConditioningError,
                errorText = "Champ invalide"
            )

            BoolSwitchField(
                label = "Télévision",
                checked = hasTv,
                onCheckedChange = { hasTv = it; hasTvError = false },
                error = hasTvError,
                errorText = "Champ invalide"
            )

            BoolSwitchField(
                label = "Parking",
                checked = hasParking,
                onCheckedChange = { hasParking = it; hasParkingError = false },
                error = hasParkingError,
                errorText = "Champ invalide"
            )

            IntField(
                label = "Capacité maximale (invités)",
                value = maxGuest,
                onValueChange = { maxGuest = it; maxGuestError = false },
                min = 1,
                max = 50,
                error = maxGuestError,
                errorText = "Valeur invalide (min 1)"
            )

            FormTextField(
                value = address,
                onValueChange = { address = it; addressError = false },
                label = "Adresse",
                error = addressError,
                errorText = "Adresse requise",
                singleLine = true
            )

            FormTextField(
                value = zipCode,
                onValueChange = { zipCode = it; zipCodeError = false },
                label = "Code postal",
                error = zipCodeError,
                errorText = "Code postal invalide",
                singleLine = true
            )

            FormTextField(
                value = city,
                onValueChange = { city = it; cityError = false },
                label = "Ville",
                error = cityError,
                errorText = "Ville requise",
                singleLine = true
            )

            FormTextField(
                value = country,
                onValueChange = { country = it; countryError = false },
                label = "Pays",
                error = countryError,
                errorText = "Pays requis",
                singleLine = true
            )

            IntField(
                label = "Prix par nuit",
                value = priceByNight,
                onValueChange = { priceByNight = it; priceByNightError = false },
                min = 0,
                max = 100000,
                error = priceByNightError,
                errorText = "Prix invalide"
            )

            if (connexionError) {
                Text(
                    text = "Erreur de connexion. Veuillez réessayer.",
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    titleError = title.isBlank() || title.length > 200
                    descriptionError = description.isBlank() || description.length > 2000
                    typeError = type.isBlank() || type.length > 50
                    numberOfRoomsError = numberOfRooms < 1
                    numberOfBathroomsError = numberOfBathrooms < 1
                    numberOfBedError = numberOfBed < 1
                    maxGuestError = maxGuest < 1
                    priceByNightError = priceByNight < 1
                    addressError = address.isBlank()
                    zipCodeError = zipCode.isBlank()
                    cityError = city.isBlank()
                    countryError = country.isBlank()

                    if (!titleError && !descriptionError && !typeError &&
                        !numberOfRoomsError && !numberOfBathroomsError &&
                        !numberOfBedError && !maxGuestError &&
                        !priceByNightError && !addressError &&
                        !zipCodeError && !cityError && !countryError) {
                        scope.launch {
                            val success = createProposalScreenPost()
                            if (success) {
                                onBackClick()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Créer")
            }
        }
    }
}
