@file:JvmName("FilterHousePropertyScreenKt")

package com.groupe1.app_android.ui.screens.filterListing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.components.PropertyTypeSelector
import com.groupe1.app_android.ui.components.StepperWithTitle
import com.groupe1.app_android.ui.theme.HoneyYellow
import com.groupe1.app_android.viewModels.FiltersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterHousePropertyScreen(
    viewModel: FiltersViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit,
    onQuit: () -> Unit
) {
    val filters by viewModel.filters.collectAsState()

    var propertyType by remember { mutableStateOf(filters.propertyType ?: "") }
    var priceRange by remember { mutableStateOf(filters.minPrice.toFloat()..filters.maxPrice.toFloat()) }
    var rooms by remember { mutableStateOf(filters.minRooms.takeIf { it > 0 } ?: 1) }
    var bathrooms by remember { mutableStateOf(filters.minBathrooms.takeIf { it > 0 } ?: 1) }
    var beds by remember { mutableStateOf(filters.minBeds.takeIf { it > 0 } ?: 1) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp, vertical = 50.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Préférences ?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )

                IconButton(onClick = onQuit) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Quit"
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Type de propriété
            Text(
                text = "Type de propriété",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(8.dp))
            PropertyTypeSelector(
                selected = propertyType,
                onSelectedChange = {
                    if (it == "Tout") {
                        propertyType = ""
                    }
                    propertyType = it
                }
            )

            Spacer(Modifier.height(24.dp))

            // Prix
            Text(
                text = "Prix par nuit",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(8.dp))

            // Labels for current range
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${priceRange.start.toInt()} €")
                Text(text = "${priceRange.endInclusive.toInt()} €")
            }

            RangeSlider(
                value = priceRange,
                onValueChange = { priceRange = it },
                valueRange = 1f..2000f,
                steps = 15,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // Nombre de pièces
            StepperWithTitle(
                title = "Nombre de pièces",
                subtitle = null,
                value = rooms,
                onIncrement = { rooms++ },
                onDecrement = { if (rooms > 1) rooms-- }
            )

            StepperWithTitle(
                title = "Nombre de salles de bain",
                subtitle = null,
                value = bathrooms,
                onIncrement = { bathrooms++ },
                onDecrement = { if (bathrooms > 0) bathrooms-- }
            )

            StepperWithTitle(
                title = "Nombre de lits",
                subtitle = null,
                value = beds,
                onIncrement = { beds++ },
                onDecrement = { if (beds > 0) beds-- }
            )

            Spacer(Modifier.weight(1f))

            // Apply filters
            Button(
                onClick = {
                    viewModel.updateProperty(
                        type = propertyType.ifBlank { null },
                        minPrice = priceRange.start.toInt(),
                        maxPrice = priceRange.endInclusive.toInt(),
                        rooms = rooms,
                        bathrooms = bathrooms,
                        beds = beds
                    )
                    onNext()
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HoneyYellow
                )
            ) {
                Text("Rechercher")
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun FilterHousePropertyScreenPreview() {
//    FilterHousePropertyScreen()
//}
