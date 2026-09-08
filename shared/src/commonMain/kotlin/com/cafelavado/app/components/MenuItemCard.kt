package com.cafelavado.app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cafelavado.app.models.MenuItem
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.CaramelBright
import com.cafelavado.app.theme.CaramelRose
import com.cafelavado.app.theme.CaramelSubtle
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.DarkSurfaceVariant
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

// ============================================================
//  MenuItemCard — cozy menu row with emoji glyph + price pill
// ============================================================

@Composable
fun MenuItemCard(
    item: MenuItem,
    modifier: Modifier = Modifier,
    onAdd: (MenuItem) -> Unit = {},
) {
    PressableScale(
        onClick = { onAdd(item) },
        modifier = modifier,
    ) {
        CafeLavadoCard {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Emoji glyph tile
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Caramel.copy(alpha = 0.18f),
                                    CaramelRose.copy(alpha = 0.10f),
                                ),
                            ),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = item.emoji,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = formatPrice(item.priceReais),
                        style = MaterialTheme.typography.titleMedium,
                        color = CaramelBright,
                        fontWeight = FontWeight.Bold,
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Caramel.copy(alpha = 0.18f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Adicionar",
                                tint = CaramelBright,
                                modifier = Modifier.size(12.dp),
                            )
                            Text(
                                text = "Add",
                                style = MaterialTheme.typography.labelSmall,
                                color = CaramelBright,
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun formatPrice(reais: Double): String {
    return "R$ %.2f".format(reais).replace(".", ",")
}
