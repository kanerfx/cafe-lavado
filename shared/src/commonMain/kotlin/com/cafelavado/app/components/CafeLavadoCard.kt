package com.cafelavado.app.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cafelavado.app.theme.DarkSurface
import com.cafelavado.app.theme.DividerColor

/**
 * Styled card matching the Café Lavado dark theme.
 * Dark surface background with a subtle border and generous rounding.
 */
@Composable
fun CafeLavadoCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface,
        ),
        border = BorderStroke(1.dp, DividerColor),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content,
        )
    }
}
