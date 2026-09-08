package com.cafelavado.app.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cafelavado.app.shared.generated.resources.Res
import com.cafelavado.app.shared.generated.resources.fraunces_black
import com.cafelavado.app.shared.generated.resources.fraunces_bold
import com.cafelavado.app.shared.generated.resources.fraunces_italic
import com.cafelavado.app.shared.generated.resources.fraunces_medium
import com.cafelavado.app.shared.generated.resources.fraunces_regular
import com.cafelavado.app.shared.generated.resources.fraunces_semibold
import org.jetbrains.compose.resources.Font

// ============================================================
//  Fraunces — warm, cozy, optical-size serif by Undercase Type
//  Loaded from bundled resources via Compose Multiplatform.
// ============================================================

@Composable
fun frauncesFamily(): FontFamily = FontFamily(
    Font(Res.font.fraunces_regular,   weight = FontWeight.Normal,    style = FontStyle.Normal),
    Font(Res.font.fraunces_medium,    weight = FontWeight.Medium,    style = FontStyle.Normal),
    Font(Res.font.fraunces_semibold,  weight = FontWeight.SemiBold,  style = FontStyle.Normal),
    Font(Res.font.fraunces_bold,      weight = FontWeight.Bold,      style = FontStyle.Normal),
    Font(Res.font.fraunces_black,     weight = FontWeight.Black,     style = FontStyle.Normal),
    Font(Res.font.fraunces_italic,    weight = FontWeight.Normal,    style = FontStyle.Italic),
)

@Composable
fun CafeLavadoTypography(): Typography {
    val fraunces = frauncesFamily()
    return Typography(
        displayLarge = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Black,
            fontSize = 34.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.5).sp,
        ),
        displayMedium = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.25).sp,
        ),
        displaySmall = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 30.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.25).sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 26.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.1.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.05.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.3.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.2.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.3.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.05.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = fraunces,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.4.sp,
        ),
    )
}
