@file:JvmName("FilterWhereScreenKt")

package com.groupe1.app_android.ui.screens.filterListing


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.components.SearchBar
import com.groupe1.app_android.ui.theme.HoneyYellow
import com.groupe1.app_android.viewModels.FiltersViewModel

@Composable
fun FilterWhereScreen(
    viewModel: FiltersViewModel,
    onNext: () -> Unit,
    onQuit: () -> Unit
) {
    val filters by viewModel.filters.collectAsState()
    var query by remember { mutableStateOf(filters.city) }

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

                Text(
                    text = "Où partez-vous ?",
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

            Spacer(modifier = Modifier.height(16.dp))

            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSuggestionSelected = { city ->
                    run {
                        query = city
                        viewModel.updateCity(city)
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recherches récentes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

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
//fun FilterWhereScreenPreview() {
//    FilterWhereScreen(viewModel = FiltersViewModel(), onNext = {}, onQuit = {})
//}
