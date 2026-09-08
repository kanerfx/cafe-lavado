package com.cafelavado.app.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cafelavado.app.components.CafeLavadoCard
import com.cafelavado.app.components.CardVariant
import com.cafelavado.app.models.sampleProfile
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.CaramelBright
import com.cafelavado.app.theme.CaramelRose
import com.cafelavado.app.theme.CaramelSubtle
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.DarkSurfaceVariant
import com.cafelavado.app.theme.DividerColor
import com.cafelavado.app.theme.StatusOccupied
import com.cafelavado.app.theme.TextMuted
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

@Composable
fun ProfileScreen() {
    val profile = sampleProfile
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Perfil",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Sua conta no Café Lavado",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
        )

        Spacer(Modifier.height(24.dp))

        // --- Avatar block ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = CircleShape,
                        clip = false,
                    )
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                CaramelBright.copy(alpha = 0.55f),
                                CaramelRose.copy(alpha = 0.30f),
                                DarkSurfaceVariant,
                            ),
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = profile.name.first().toString(),
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                    color = CaramelBright,
                )
            }
            Spacer(Modifier.height(14.dp))
            Text(
                text = profile.name,
                style = MaterialTheme.typography.headlineMedium,
                color = TextPrimary,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = profile.email,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
            )
        }

        Spacer(Modifier.height(28.dp))

        // --- Loyalty card ---
        CafeLavadoCard(variant = CardVariant.Warm) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Meus Pontos",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "Programa de fidelidade",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                    )
                }
                Text(
                    text = "${profile.loyaltyPoints}",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = CaramelBright,
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // --- Menu list ---
        ProfileMenuItem(icon = Icons.Default.Star,    label = "Histórico de Pedidos", tint = CaramelBright)
        ProfileMenuItem(icon = Icons.Default.Settings, label = "Configurações",        tint = Caramel)
        ProfileMenuItem(icon = Icons.Default.Help,    label = "Ajuda & Suporte",       tint = CaramelRose)
        ProfileMenuItem(
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            label = "Sair",
            tint = StatusOccupied,
        )
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    tint: Color,
) {
    Surface(
        color = DarkBackground,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(tint.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = tint,
                    modifier = Modifier.size(18.dp),
                )
            }
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = TextPrimary,
                modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(20.dp),
            )
        }
    }
    HorizontalDivider(color = DividerColor, thickness = 1.dp)
}
