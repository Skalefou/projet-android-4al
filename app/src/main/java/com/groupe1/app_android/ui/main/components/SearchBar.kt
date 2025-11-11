package com.groupe1.app_android.ui.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.groupe1.app_android.services.FilterAdService

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    var suggestions by remember { mutableStateOf<List<String>>(emptyList()) }
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {
            TextField(
                value = query,
                onValueChange = { newValue ->
                    onQueryChange(newValue)
                    searchJob?.cancel()
                    if (newValue.length >= 2) {
                        searchJob = scope.launch {
                            delay(300)
                            runCatching { FilterAdService.searchCities(newValue) }
                                .onSuccess {
                                    suggestions = it
                                    expanded = it.isNotEmpty()
                                }
                                .onFailure {
                                    suggestions = emptyList()
                                    expanded = false
                                }
                        }
                    } else {
                        suggestions = emptyList()
                        expanded = false
                    }
                },
                placeholder = { Text("Commencer ma recherche") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            suggestions.forEach { city ->
                DropdownMenuItem(
                    text = { Text(city) },
                    onClick = {
                        onQueryChange(city)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(query = "", onQueryChange = {})
}