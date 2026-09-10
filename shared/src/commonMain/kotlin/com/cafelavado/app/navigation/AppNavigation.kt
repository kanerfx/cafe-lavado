package com.cafelavado.app.navigation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cafelavado.app.components.AppMode
import com.cafelavado.app.components.SectionModeBottomBar
import com.cafelavado.app.screens.home.HomeScreen
import com.cafelavado.app.screens.laundry.LaundryScreen
import com.cafelavado.app.screens.menu.MenuScreen
import com.cafelavado.app.screens.profile.ProfileScreen
import com.cafelavado.app.theme.DarkBackground
private val LeftTabs = listOf(HomeTab, MenuTab)
private val RightTabs = listOf(LaundryTab, ProfileTab)
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
@Composable
fun AppNavigation() {
    TabNavigator(HomeTab) {
        val navigator = LocalTabNavigator.current
        Scaffold(
            containerColor = DarkBackground,
            bottomBar = {
                SectionModeBottomBar(
                    leftTabs = LeftTabs,
                    rightTabs = RightTabs,
                    onTabClick = { tab ->
                        navigator.current = tab
                    },
                )
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                CurrentTab()
            }
        }
    }
}
