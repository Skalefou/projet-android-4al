package com.groupe1.app_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.groupe1.app_android.navigation.AppNav
import com.groupe1.app_android.ui.theme.ProjetandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetandroidTheme(dynamicColor = false) {
                Surface {
                    val nav = rememberNavController()
                    AppNav(nav)
                }
            }
        }
    }
}