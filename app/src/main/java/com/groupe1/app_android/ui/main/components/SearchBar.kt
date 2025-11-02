package com.groupe1.app_android.ui.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(58.dp)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {Text("Commencer ma recherche")},
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(query = "", onQueryChange = {})
}