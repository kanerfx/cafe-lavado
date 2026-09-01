package com.cafelavado.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cafelavado.app.screens.home.HomeScreen
import com.cafelavado.app.screens.laundry.LaundryScreen
import com.cafelavado.app.screens.menu.MenuScreen
import com.cafelavado.app.screens.profile.ProfileScreen
import com.cafelavado.app.theme.*

// ── Tab definitions ─────────────────────────────────────────────

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Home)
            return remember { TabOptions(index = 0u, title = "Início", icon = icon) }
        }

    @Composable
    override fun Content() = HomeScreen()
}

object MenuTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.AutoMirrored.Filled.List)
            return remember { TabOptions(index = 1u, title = "Cardápio", icon = icon) }
        }

    @Composable
    override fun Content() = MenuScreen()
}

object LaundryTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Refresh)
            return remember { TabOptions(index = 2u, title = "Lavanderia", icon = icon) }
        }

    @Composable
    override fun Content() = LaundryScreen()
}

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Person)
            return remember { TabOptions(index = 3u, title = "Perfil", icon = icon) }
        }

    @Composable
    override fun Content() = ProfileScreen()
}

// ── Main scaffold with bottom navigation ────────────────────────

@Composable
fun AppNavigation() {
    TabNavigator(HomeTab) {
        Scaffold(
            containerColor = DarkBackground,
            bottomBar = { BottomBar() },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                CurrentTab()
            }
        }
    }
}

@Composable
private fun BottomBar() {
    val tabNavigator = LocalTabNavigator.current
    val tabs = listOf(HomeTab, MenuTab, LaundryTab, ProfileTab)

    NavigationBar(
        containerColor = DarkSurface,
        contentColor = TextPrimary,
        tonalElevation = 0.dp,
    ) {
        tabs.forEach { tab ->
            val selected = tabNavigator.current.options.index == tab.options.index

            NavigationBarItem(
                selected = selected,
                onClick = { tabNavigator.current = tab },
                icon = {
                    tab.options.icon?.let {
                        Icon(painter = it, contentDescription = tab.options.title)
                    }
                },
                label = {
                    Text(
                        text = tab.options.title,
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor   = LuminousBlueBright,
                    selectedTextColor   = LuminousBlueBright,
                    indicatorColor      = LuminousBlueSubtle,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted,
                ),
            )
        }
    }
}
