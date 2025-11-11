package com.groupe1.app_android.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.ui.main.components.SearchBar

@Composable
fun HomeScreen() {
    var query by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp)
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}