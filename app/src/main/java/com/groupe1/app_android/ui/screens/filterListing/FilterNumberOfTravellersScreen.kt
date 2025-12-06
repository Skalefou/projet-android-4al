package com.groupe1.app_android.ui.screens.filterListing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.theme.HoneyYellow
import com.groupe1.app_android.R
import com.groupe1.app_android.ui.components.StepperWithTitle

@Composable
fun FilterNumberOfTravellersScreen() {
    var adults by remember { mutableStateOf(1) }
    var children by remember { mutableStateOf(0) }
    var babies by remember { mutableStateOf(0) }
    var pets by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(25.dp, 50.dp, 25.dp, 50.dp)
        ) {
            // Title
            Text(
                text = "Combien de voyageurs ?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(30.dp))

            StepperWithTitle(
                title = "Adultes",
                subtitle = "13 ans ou plus",
                value = adults,
                min = 1, // au moins 1 adulte
                onIncrement = { adults++ },
                onDecrement = { if (adults > 1) adults-- }
            )

            StepperWithTitle(
                title = "Enfants",
                subtitle = "De 2 à 12 ans",
                value = children,
                onIncrement = { children++ },
                onDecrement = { if (children > 0) children-- }
            )

            StepperWithTitle(
                title = "Bébés",
                subtitle = "- de 2 ans",
                value = babies,
                onIncrement = { babies++ },
                onDecrement = { if (babies > 0) babies-- }
            )

            StepperWithTitle(
                title = "Animaux domestiques",
                subtitle = null,
                value = pets,
                onIncrement = { pets++ },
                onDecrement = { if (pets > 0) pets-- }
            )

            Spacer(Modifier.weight(1f))

            // Rechercher button
            Button(
                onClick = {
                    // TODO use adults, children, babies, pets
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

@Preview(showBackground = true)
@Composable
fun FilterNumberOfTravellersScreenPreview() {
    FilterNumberOfTravellersScreen()
}
