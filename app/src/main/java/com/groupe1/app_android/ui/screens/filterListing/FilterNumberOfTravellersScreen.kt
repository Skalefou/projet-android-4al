package com.groupe1.app_android.ui.screens.filterListing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.theme.HoneyYellow
import com.groupe1.app_android.ui.components.StepperWithTitle
import com.groupe1.app_android.viewModels.FiltersViewModel

@Composable
fun FilterNumberOfTravellersScreen(
    viewModel: FiltersViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit,
    onQuit: () -> Unit
) {
    val filters by viewModel.filters.collectAsState()

    var adults by remember { mutableIntStateOf(filters.adults) }
    var children by remember { mutableIntStateOf(filters.children) }
    var babies by remember { mutableIntStateOf(filters.babies) }
    var pets by remember { mutableIntStateOf(filters.pets) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(25.dp, 50.dp, 25.dp, 50.dp)
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
                    text = "Combien de voyageurs ?",
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

            Spacer(Modifier.height(30.dp))

            StepperWithTitle(
                title = "Adultes",
                subtitle = "13 ans ou plus",
                value = adults,
                min = 1,
                onIncrement = { adults++; viewModel.updateTravellers(adults, children, babies, pets) },
                onDecrement = { if (adults > 1) { adults--; viewModel.updateTravellers(adults, children, babies, pets) } }
            )

            StepperWithTitle(
                title = "Enfants",
                subtitle = "De 2 à 12 ans",
                value = children,
                onIncrement = { children++; viewModel.updateTravellers(adults, children, babies, pets)  },
                onDecrement = { if (children > 0) { children--; viewModel.updateTravellers(adults, children, babies, pets) } }
            )

            StepperWithTitle(
                title = "Bébés",
                subtitle = "- de 2 ans",
                value = babies,
                onIncrement = { babies++; viewModel.updateTravellers(adults, children, babies, pets) },
                onDecrement = { if (babies > 0) { babies--; viewModel.updateTravellers(adults, children, babies, pets) } }
            )

            StepperWithTitle(
                title = "Animaux domestiques",
                subtitle = null,
                value = pets,
                onIncrement = { pets++; viewModel.updateTravellers(adults, children, babies, pets) },
                onDecrement = { if (pets > 0) { pets--; viewModel.updateTravellers(adults, children, babies, pets) } }
            )

            Spacer(Modifier.weight(1f))

            // Rechercher button
            Button(
                onClick = onNext,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HoneyYellow
                )
            ) {
                Text("Suivant")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FilterNumberOfTravellersScreenPreview() {
//    FilterNumberOfTravellersScreen()
//}
