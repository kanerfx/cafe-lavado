package com.cafelavado.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CafeLavadoDarkScheme = darkColorScheme(
    primary              = Caramel,
    onPrimary            = DarkBackground,
    primaryContainer     = CaramelSubtle,
    onPrimaryContainer   = TextPrimary,
    secondary            = CaramelBright,
    onSecondary          = DarkBackground,
    secondaryContainer   = CaramelSubtle,
    onSecondaryContainer = TextPrimary,
    tertiary             = CaramelRose,
    onTertiary           = TextPrimary,
    background           = DarkBackground,
    onBackground         = TextPrimary,
    surface              = DarkSurface,
    onSurface            = TextPrimary,
    surfaceVariant       = DarkSurfaceVariant,
    onSurfaceVariant     = TextSecondary,
    surfaceContainerHigh = DarkSurfaceHigh,
    outline              = TextMuted,
    outlineVariant       = DividerColor,
    error                = StatusOccupied,
    onError              = TextPrimary,
)

@Composable
fun CafeLavadoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CafeLavadoDarkScheme,
        typography  = CafeLavadoTypography(),
        shapes      = CafeLavadoShapes,
        content     = content,
    )
}

