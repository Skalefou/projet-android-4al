package com.groupe1.app_android.ui.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color = Color.LightGray
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Surface(
        shape = CircleShape,
        color = backgroundColor,
        contentColor = Color.Transparent,
        shadowElevation = 4.dp,
        modifier = Modifier
            .size(38.dp)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = ripple(
                    bounded = true,
                    radius = 19.dp,
                    color = Color.Black.copy(alpha = 0.2f)
                )
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF1C1C1C),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundIconButtonPreview() {
    RoundIconButton(
        icon = Icons.Default.Add,
        onClick = {}
    )
}

