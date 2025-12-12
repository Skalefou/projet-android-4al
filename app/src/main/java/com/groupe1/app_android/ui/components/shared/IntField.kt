package com.groupe1.app_android.ui.components.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.theme.defaultOutlinedTextFieldColors
import kotlin.math.max
import kotlin.math.min

@Composable
fun IntField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    min: Int,
    max: Int,
    error: Boolean,
    errorText: String
) {
    var text by remember(value) { mutableStateOf(value.toString()) }

    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
        Text(label, modifier = Modifier.padding(bottom = 4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    val newValue = max(min, value - 1)
                    onValueChange(newValue)
                }
            ) { Text("−") }

            Spacer(Modifier.width(8.dp))

            OutlinedTextField(
                value = text,
                onValueChange = { raw ->
                    val filtered = raw.filter { it.isDigit() }
                    text = filtered
                    if (filtered.isNotBlank()) {
                        val parsed = filtered.toIntOrNull()
                        if (parsed != null) {
                            onValueChange(min(max, max(min, parsed)))
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                singleLine = true,
                isError = error,
                colors = defaultOutlinedTextFieldColors(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.width(8.dp))

            OutlinedButton(
                onClick = {
                    val newValue = min(max, value + 1)
                    onValueChange(newValue)
                }
            ) { Text("+") }
        }

        if (error) {
            Text(errorText, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}