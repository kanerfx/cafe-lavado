package com.cafelavado.app.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cafelavado.app.theme.CardBorder
import com.cafelavado.app.theme.CardBorderWarm
import com.cafelavado.app.theme.DarkSurface
import com.cafelavado.app.theme.DarkSurfaceHigh
import com.cafelavado.app.theme.DarkSurfaceVariant

// ============================================================
//  CafeLavadoCard
//  ------------------------------------------------------------
//  Glassmorphic warm card with:
//    - vertical gradient (lighter at top, deeper at bottom)
//    - soft drop shadow
//    - subtle caramel border
//  Use `variant` to pick the surface tone.
// ============================================================

enum class CardVariant { Default, Warm, Flat }

@Composable
fun CafeLavadoCard(
    modifier: Modifier = Modifier,
    variant: CardVariant = CardVariant.Default,
    content: @Composable ColumnScope.() -> Unit,
) {
    val (container, border) = when (variant) {
        CardVariant.Default -> DarkSurface to CardBorder
        CardVariant.Warm    -> DarkSurfaceHigh to CardBorderWarm
        CardVariant.Flat    -> Color.Transparent to Color.Transparent
    }

    val gradient = Brush.verticalGradient(
        colors = when (variant) {
            CardVariant.Default -> listOf(DarkSurfaceVariant, DarkSurface)
            CardVariant.Warm    -> listOf(DarkSurfaceHigh, DarkSurfaceVariant)
            CardVariant.Flat    -> listOf(Color.Transparent, Color.Transparent)
        },
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (variant == CardVariant.Flat) 0.dp else 8.dp,
                shape = RoundedCornerShape(18.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.6f),
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = container),
        border = BorderStroke(1.dp, border),
    ) {
        Column(
            modifier = Modifier
                .background(gradient)
                .padding(16.dp),
            content = content,
        )
    }
}
