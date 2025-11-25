package com.groupe1.app_android.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.ui.main.components.FilterAdTriggerButton

@Composable
fun HomeScreen(
    onTriggerFilterAd: () -> Unit,
    onOpenProfile: () -> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp)
        ) {
            FilterAdTriggerButton( placeholder = "Commencer ma recherche",
                onClick = { onTriggerFilterAd() })

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { onOpenProfile() }) {
                Text(text = "Profil")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onTriggerFilterAd = {println("Start filtering ad!") }, onOpenProfile = { println("Open profile") })
}