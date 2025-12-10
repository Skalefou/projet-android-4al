package com.groupe1.app_android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.groupe1.app_android.domain.models.User
import com.groupe1.app_android.ui.components.BackButton

@Composable
fun CreateProposalScreen(
    currentUser: User,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        BackButton(
            modifier = Modifier.align(Alignment.Start),
            onClick = { onBackClick() }
        )
    }
}