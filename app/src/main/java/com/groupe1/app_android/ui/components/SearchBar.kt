package com.groupe1.app_android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.data.repository.FilterListingRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSuggestionSelected: (String) -> Unit
) {
    var suggestions by remember { mutableStateOf<List<String>>(emptyList()) }
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }
    val filterListingRepository = FilterListingRepositoryImpl()

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
                            delay(200)
                            runCatching { filterListingRepository.searchCities(newValue) }
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
                placeholder = { Text("Rechercher une destination") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF6F6F6),
                    unfocusedContainerColor = Color(0xFFF6F6F6),
                    disabledContainerColor = Color(0xFFF6F6F6),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.DarkGray
                ),
                modifier = Modifier.fillMaxWidth().height(56.dp)
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
                        onSuggestionSelected(city)
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
    SearchBar(
        query = "", onQueryChange = {},
        onSuggestionSelected = {}
    )
}