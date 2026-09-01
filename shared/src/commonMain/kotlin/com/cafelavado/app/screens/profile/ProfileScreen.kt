package com.cafelavado.app.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cafelavado.app.components.CafeLavadoCard
import com.cafelavado.app.models.sampleProfile
import com.cafelavado.app.theme.*

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

        // ── Header ──────────────────────────────────────────────
        Text(
            text = "Perfil",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
        )

        Spacer(Modifier.height(24.dp))

        // ── Avatar + name ───────────────────────────────────────
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(DarkSurfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = profile.name.first().toString(),
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                    color = LuminousBlueLight,
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

        // ── Loyalty card ────────────────────────────────────────
        CafeLavadoCard {
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
                    color = LuminousBlueBright,
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── Menu items ──────────────────────────────────────────
        ProfileMenuItem(icon = Icons.Default.Star,    label = "Histórico de Pedidos")
        ProfileMenuItem(icon = Icons.Default.Settings, label = "Configurações")
        ProfileMenuItem(icon = Icons.Default.Info,     label = "Ajuda & Suporte")
        ProfileMenuItem(icon = Icons.AutoMirrored.Filled.ExitToApp, label = "Sair", tint = StatusOccupied)

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    tint: androidx.compose.ui.graphics.Color = TextSecondary,
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
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = tint,
                modifier = Modifier.size(22.dp),
            )
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
