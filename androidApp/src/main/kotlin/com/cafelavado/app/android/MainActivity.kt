package com.cafelavado.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cafelavado.app.data.appModule
import com.cafelavado.app.navigation.AppNavigation
import com.cafelavado.app.theme.CafeLavadoTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start Koin once per process.
        startKoin {
            modules(appModule)
        }

        enableEdgeToEdge()
        setContent {
            CafeLavadoTheme {
                AppNavigation()
            }
        }
    }
}
