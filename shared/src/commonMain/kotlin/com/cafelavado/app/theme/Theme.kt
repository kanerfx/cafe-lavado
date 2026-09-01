package com.cafelavado.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CafeLavadoDarkScheme = darkColorScheme(
    primary              = LuminousBlue,
    onPrimary            = TextPrimary,
    primaryContainer     = LuminousBlueSubtle,
    onPrimaryContainer   = TextPrimary,
    secondary            = LuminousBlueBright,
    onSecondary          = TextPrimary,
    secondaryContainer   = LuminousBlueSubtle,
    onSecondaryContainer = TextPrimary,
    tertiary             = LuminousBlueLight,
    onTertiary           = DarkBackground,
    background           = DarkBackground,
    onBackground         = TextPrimary,
    surface              = DarkSurface,
    onSurface            = TextPrimary,
    surfaceVariant       = DarkSurfaceVariant,
    onSurfaceVariant     = TextSecondary,
    surfaceContainerHigh = DarkSurfaceHigh,
    outline              = TextMuted,
    outlineVariant       = DividerColor,
)

@Composable
fun CafeLavadoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CafeLavadoDarkScheme,
        typography  = CafeLavadoTypography,
        shapes      = CafeLavadoShapes,
        content     = content,
    )
}
