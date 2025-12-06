package com.groupe1.app_android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun DateRangeHeadline(
    startDate: Long?,
    endDate: Long?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        DateBox(
            label = "Arrivée",
            dateMillis = startDate,
            modifier = Modifier.weight(1f)
        )

        DateBox(
            label = "Départ",
            dateMillis = endDate,
            modifier = Modifier.weight(1f)
        )
    }
}
/**
 * Small card for “Check in / Check out” date.
 * When dateMillis changes, Compose recomposes and the text updates.
 */
@Composable
fun DateBox(
    label: String,
    dateMillis: Long?,
    modifier: Modifier = Modifier
) {
    val formatter = remember {
        SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    }

    val dateText = dateMillis?.let { formatter.format(Date(it)) } ?: "Ajouter une date"

    Surface(
        modifier = modifier.height(72.dp),
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = dateText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (dateMillis != null) FontWeight.Bold else FontWeight.Normal
                )
            )
        }
    }
}


