package com.groupe1.app_android.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.models.User
import com.groupe1.app_android.preview.PreviewUser
import com.groupe1.app_android.ui.components.FilterAdTriggerButton

@Composable
fun HomeScreen(
    currentUser: User,
    onTriggerFilterAd: () -> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp)
        ) {
            FilterAdTriggerButton( placeholder = "Commencer ma recherche",
                onClick = { onTriggerFilterAd() })

            Text(
                "Bonjour ${currentUser.firstName} !",
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onTriggerFilterAd = { println("Start filtering ad!") },
        currentUser = PreviewUser.Default
    )
}
