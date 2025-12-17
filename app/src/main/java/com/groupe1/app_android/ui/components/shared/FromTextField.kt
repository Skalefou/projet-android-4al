package com.groupe1.app_android.ui.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.theme.defaultOutlinedTextFieldColors

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: Boolean,
    errorText: String,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = error,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = defaultOutlinedTextFieldColors(),
        singleLine = singleLine
    )
    if (error) {
        Text(errorText, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(bottom = 8.dp))
    }
}