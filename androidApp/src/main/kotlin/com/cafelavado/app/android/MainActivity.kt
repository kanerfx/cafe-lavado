package com.cafelavado.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cafelavado.app.navigation.AppNavigation
import com.cafelavado.app.theme.CafeLavadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CafeLavadoTheme {
                AppNavigation()
            }
        }
    }
}
