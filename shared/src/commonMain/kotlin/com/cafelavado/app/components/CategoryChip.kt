package com.cafelavado.app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.CaramelBright
import com.cafelavado.app.theme.CaramelRose
import com.cafelavado.app.theme.CaramelSubtle
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.DarkSurfaceVariant
import com.cafelavado.app.theme.DividerColor
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

// ============================================================
//  CategoryChip — animated filter chip with caramel gradient
//  when selected.
// ============================================================

@Composable
fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val containerColor by animateColorAsState(
        targetValue = if (selected) Caramel else DarkSurfaceVariant,
        animationSpec = tween(220),
        label = "chip-container",
    )
    val contentColor by animateColorAsState(
        targetValue = if (selected) DarkBackground else TextSecondary,
        animationSpec = tween(220),
        label = "chip-content",
    )

    if (selected) {
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, DividerColor),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = DarkSurfaceVariant,
                contentColor = contentColor,
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    }
}
