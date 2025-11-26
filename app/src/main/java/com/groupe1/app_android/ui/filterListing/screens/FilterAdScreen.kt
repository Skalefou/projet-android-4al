package com.groupe1.app_android.ui.filterListing.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.ui.filterListing.components.SearchBar
import com.groupe1.app_android.ui.theme.HoneyYellow

@Composable
fun FilterAdScreen() {
    var query by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp)
        ) {
            Text("Où ?")
            SearchBar(
                query = query,
                onQueryChange = { query = it }
            )
            Text("Recherches récentes")
            Button(onClick = {},
                shape = RoundedCornerShape(50),
                modifier = Modifier.height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HoneyYellow)
            ) {
                Text("Suivant")
            }
        }
    }
}

@Preview
@Composable
fun FilterAdScreenPreview() {
    FilterAdScreen()
}