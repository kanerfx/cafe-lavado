package com.cafelavado.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cafelavado.app.models.MenuItem
import com.cafelavado.app.theme.*

/**
 * Displays a café menu item with a placeholder image area,
 * name, description, and price.
 */
@Composable
fun MenuItemCard(
    item: MenuItem,
    modifier: Modifier = Modifier,
) {
    CafeLavadoCard(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Image placeholder — replace with actual product images
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(DarkSurfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = item.name.first().toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = LuminousBlueLight,
                )
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary,
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    maxLines = 2,
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = "R$ %.2f".format(item.priceReais),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = LuminousBlueBright,
            )
        }
    }
}
