package com.cafelavado.app.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cafelavado.app.theme.*

/**
 * A filter chip used to toggle menu categories.
 */
@Composable
fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (selected) {
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier,
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = LuminousBlue,
                contentColor = TextPrimary,
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, DividerColor),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TextSecondary,
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    }
}
