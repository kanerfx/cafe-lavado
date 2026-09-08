package com.cafelavado.app.navigation

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cafelavado.app.screens.home.HomeScreen
import com.cafelavado.app.screens.laundry.LaundryScreen
import com.cafelavado.app.screens.menu.MenuScreen
import com.cafelavado.app.screens.profile.ProfileScreen
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.CaramelBright
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.DarkSurface
import com.cafelavado.app.theme.DarkSurfaceVariant
import com.cafelavado.app.theme.TextMuted
import kotlin.math.PI
import kotlin.math.sin

// ============================================================
//  AppNavigation — Voyager TabNavigator with Washing Machine
//  animated bottom bar (water wave highlight effect).
// ============================================================

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainterCompat(Icons.Default.Home)
            return remember { TabOptions(index = 0u, title = "Início", icon = icon) }
        }
    @Composable override fun Content() = HomeScreen()
}

object MenuTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainterCompat(Icons.AutoMirrored.Filled.List)
            return remember { TabOptions(index = 1u, title = "Cardápio", icon = icon) }
        }
    @Composable override fun Content() = MenuScreen()
}

object LaundryTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainterCompat(Icons.Default.Refresh)
            return remember { TabOptions(index = 2u, title = "Lavanderia", icon = icon) }
        }
    @Composable override fun Content() = LaundryScreen()
}

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainterCompat(Icons.Default.Person)
            return remember { TabOptions(index = 3u, title = "Perfil", icon = icon) }
        }
    @Composable override fun Content() = ProfileScreen()
}

@Composable
fun AppNavigation() {
    TabNavigator(HomeTab) {
        androidx.compose.material3.Scaffold(
            containerColor = DarkBackground,
            bottomBar = { WashingMachineBottomBar() },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                CurrentTab()
            }
        }
    }
}

// ============================================================
//  WashingMachineBottomBar
//  Custom bottom nav with a water-wave fill animation on the
//  selected item — water sloshing inside a laundry drum.
// ============================================================

@Composable
private fun WashingMachineBottomBar() {
    val tabNavigator = LocalTabNavigator.current
    val tabs = listOf(HomeTab, MenuTab, LaundryTab, ProfileTab)

    // Shared continuous wave phases — all items use the same clock
    val infinite = rememberInfiniteTransition(label = "wave")
    val wave1Phase by infinite.animateFloat(
        initialValue = 0f,
        targetValue = (2f * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2_800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "wave1",
    )
    val wave2Phase by infinite.animateFloat(
        initialValue = PI.toFloat(),
        targetValue = (3f * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3_700, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "wave2",
    )
    val sloshY by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2_200, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "slosh",
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        DarkSurface.copy(alpha = 0.0f),
                        DarkSurface,
                    ),
                )
            ),
    ) {
        // Top hairline with blue glow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Caramel.copy(alpha = 0.35f),
                            CaramelBright.copy(alpha = 0.65f),
                            Caramel.copy(alpha = 0.35f),
                            Color.Transparent,
                        ),
                    )
                )
                .align(Alignment.TopCenter),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            tabs.forEach { tab ->
                val isSelected = tabNavigator.current.options.index == tab.options.index

                // ── Water fill progress: 0 = empty, 1 = full ──────────────────
                // Bouncy spring so water "sloshes" in on select, drains on deselect
                val fillProgress by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = spring(
                        dampingRatio = 0.65f,
                        stiffness = 180f,
                    ),
                    label = "fill-${tab.options.index}",
                )

                // ── Icon scale: spring bounce on select ────────────────────────
                val iconScale by animateFloatAsState(
                    targetValue = if (isSelected) 1.14f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium,
                    ),
                    label = "iconScale-${tab.options.index}",
                )

                // ── Label alpha: fades in with the water ──────────────────────
                val labelAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = tween(durationMillis = 250),
                    label = "labelAlpha-${tab.options.index}",
                )

                // ── Icon tint: animates from muted to white ───────────────────
                val iconAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = tween(durationMillis = 300),
                    label = "iconAlpha-${tab.options.index}",
                )

                WashingMachineNavItem(
                    tab = tab,
                    isSelected = isSelected,
                    fillProgress = fillProgress,
                    iconScale = iconScale,
                    labelAlpha = labelAlpha,
                    iconAlpha = iconAlpha,
                    wave1Phase = wave1Phase,
                    wave2Phase = wave2Phase,
                    sloshY = sloshY,
                    onClick = { tabNavigator.current = tab },
                )
            }
        }
    }
}

@Composable
private fun WashingMachineNavItem(
    tab: Tab,
    isSelected: Boolean,
    fillProgress: Float,       // 0f = empty, 1f = full (animated)
    iconScale: Float,
    labelAlpha: Float,
    iconAlpha: Float,          // 0f = muted, 1f = white (animated)
    wave1Phase: Float,
    wave2Phase: Float,
    sloshY: Float,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 4.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width = 62.dp, height = 44.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .drawWithContent {
                    val w = size.width
                    val h = size.height
                    val r = h / 2f
                    val cornerR = CornerRadius(r)

                    // ── Always draw the dark capsule background ────────────────
                    // Alpha scales with fillProgress so it fades in with the water
                    drawRoundRect(
                        color = DarkSurfaceVariant.copy(alpha = fillProgress.coerceIn(0f, 1f)),
                        cornerRadius = cornerR,
                    )

                    if (fillProgress > 0.01f) {
                        // ── Water level rises as fillProgress increases ─────────
                        // At fillProgress=1 → water at steady-state (52–62% fill)
                        // At fillProgress=0 → water below the bottom edge (off screen)
                        val steadyFill = 0.54f + sloshY * 0.08f
                        // Water rises from bottom: at fillProgress=0 waterY=h (bottom), at 1 waterY=normal
                        val waterY = h - (h * steadyFill * fillProgress)

                        // ── Wave path 1 (primary, slower, deeper blue) ─────────
                        val path1 = Path().apply {
                            val amplitude = 3.5.dp.toPx() * fillProgress
                            val frequency = 2f
                            moveTo(0f, waterY)
                            var x = 0f
                            while (x <= w) {
                                val y = waterY + amplitude * sin(
                                    (x / w) * frequency * 2f * PI.toFloat() + wave1Phase
                                )
                                lineTo(x, y)
                                x += 2f
                            }
                            lineTo(w, h)
                            lineTo(0f, h)
                            close()
                        }
                        drawPath(
                            path = path1,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Caramel.copy(alpha = 0.85f * fillProgress),
                                    Caramel.copy(alpha = 0.55f * fillProgress),
                                    CaramelBright.copy(alpha = 0.30f * fillProgress),
                                ),
                                startY = waterY,
                                endY = h,
                            ),
                        )

                        // ── Wave path 2 (secondary, faster, lighter) ───────────
                        val path2 = Path().apply {
                            val amplitude = 2.5.dp.toPx() * fillProgress
                            val frequency = 3f
                            val offsetY = 3.dp.toPx()
                            moveTo(0f, waterY + offsetY)
                            var x = 0f
                            while (x <= w) {
                                val y = waterY + offsetY + amplitude * sin(
                                    (x / w) * frequency * 2f * PI.toFloat() + wave2Phase
                                )
                                lineTo(x, y)
                                x += 2f
                            }
                            lineTo(w, h)
                            lineTo(0f, h)
                            close()
                        }
                        drawPath(
                            path = path2,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    CaramelBright.copy(alpha = 0.55f * fillProgress),
                                    Caramel.copy(alpha = 0.20f * fillProgress),
                                    Color.Transparent,
                                ),
                                startY = waterY,
                                endY = h,
                            ),
                        )

                        // ── Soft radial light from inside the water body ────────
                        // drawCircle fades naturally — no hard boundary visible
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Caramel.copy(alpha = 0.28f * fillProgress),
                                    Color.Transparent,
                                ),
                                center = Offset(w * 0.5f, waterY + (h - waterY) * 0.4f),
                                radius = w * 0.7f,
                            ),
                            radius = w * 0.7f,
                            center = Offset(w * 0.5f, waterY + (h - waterY) * 0.4f),
                        )

                        // ── Very soft foam shimmer just above the waterline ────
                        // Drawn as a Circle so it blends freely into the water
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.20f * fillProgress),
                                    Color.Transparent,
                                ),
                                center = Offset(w * 0.4f, waterY),
                                radius = w * 0.45f,
                            ),
                            radius = w * 0.45f,
                            center = Offset(w * 0.4f, waterY),
                        )
                    }

                    // Draw icon content on top
                    drawContent()
                },
        ) {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    // Blend from TextMuted → White as water fills in
                    tint = androidx.compose.ui.graphics.lerp(TextMuted, Color.White, iconAlpha),
                    modifier = Modifier.size(22.dp),
                )
            }
        }

        // Label fades in as water fills; space always reserved
        Text(
            text = tab.options.title,
            style = MaterialTheme.typography.labelSmall,
            color = CaramelBright.copy(alpha = labelAlpha),
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

// Small wrapper so we don't need to thread the painter import through everywhere.
@Composable
private fun rememberVectorPainterCompat(imageVector: androidx.compose.ui.graphics.vector.ImageVector) =
    androidx.compose.ui.graphics.vector.rememberVectorPainter(imageVector)

