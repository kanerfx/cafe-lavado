package com.cafelavado.app.theme

import androidx.compose.ui.graphics.Color

// ============================================================
//  Café Lavado — Luminous Blue Palette
//  Deep near-black base + electric cyan-blue accents.
//  High contrast, premium, modern dark theme.
// ============================================================

// --- Surfaces (near-black with blue undertone) ---
val DarkBackground     = Color(0xFF060A12)   // near-black navy
val DarkSurface        = Color(0xFF0B1120)   // deep navy
val DarkSurfaceVariant = Color(0xFF111827)   // dark blue-grey
val DarkSurfaceHigh    = Color(0xFF192035)   // navy elevated
val DarkSurfaceHighest = Color(0xFF1E2A42)   // navy high

// --- Brand: Luminous Blue (electric cyan/azure) ---
val Caramel            = Color(0xFF38BDF8)   // sky-blue (primary accent)
val CaramelBright      = Color(0xFF7DD3FC)   // light sky-blue (bright)
val CaramelSoft        = Color(0xFF1E6FA8)   // muted blue
val CaramelSubtle      = Color(0xFF0C2A4A)   // dark blue container
val CaramelRose        = Color(0xFF0EA5E9)   // vivid azure

// --- Text ---
val TextPrimary        = Color(0xFFF0F6FF)   // near-white with blue tint
val TextSecondary      = Color(0xFFAEC6E8)   // soft blue-grey
val TextMuted          = Color(0xFF5C7A9E)   // muted blue-grey
val TextFaint          = Color(0xFF2E4A6A)   // faint navy

// --- Status ---
val StatusFree         = Color(0xFF34D399)   // emerald
val StatusFreeSoft     = Color(0xFF065F46)
val StatusOccupied     = Color(0xFFF87171)   // rose-red
val StatusOccupiedSoft = Color(0xFF7F1D1D)
val StatusReserved     = Color(0xFFFBBF24)   // amber
val StatusReservedSoft = Color(0xFF78350F)

// --- Lines & overlays ---
val DividerColor       = Color(0x2238BDF8)   // blue 13%
val CardBorder         = Color(0x1A38BDF8)   // blue 10%
val CardBorderWarm     = Color(0x3038BDF8)   // blue 19%
val ScrimOverlay       = Color(0xE6020608)   // almost-opaque deep navy-black

// --- True Luminous Blue aliases ---
val LuminousBlue        = Caramel
val LuminousBlueBright  = CaramelBright
val LuminousBlueLight   = CaramelBright
val LuminousBlueSubtle  = CaramelSubtle
