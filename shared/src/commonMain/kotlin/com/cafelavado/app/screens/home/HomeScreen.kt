package com.cafelavado.app.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cafelavado.app.components.CafeLavadoCard
import com.cafelavado.app.components.MachineStatusCard
import com.cafelavado.app.models.MachineStatus
import com.cafelavado.app.models.sampleMachines
import com.cafelavado.app.theme.*

@Composable
fun HomeScreen() {
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
            text = "CAFÉ LAVADO",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 2.5.sp,
            ),
            color = TextPrimary,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Café & Lavanderia",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
        )

        Spacer(Modifier.height(28.dp))

        // ── Minhas Máquinas (active machines summary) ───────────
        SectionHeader("Minhas Máquinas")
        Spacer(Modifier.height(10.dp))

        val activeMachines = sampleMachines.filter { it.status == MachineStatus.OCCUPIED }
        if (activeMachines.isEmpty()) {
            CafeLavadoCard {
                Text(
                    text = "Nenhuma máquina ativa no momento.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                )
            }
        } else {
            activeMachines.forEach { machine ->
                MachineStatusCard(machine = machine)
                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── Ações Rápidas ───────────────────────────────────────
        SectionHeader("Ações Rápidas")
        Spacer(Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            QuickActionTile(
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = LuminousBlueLight) },
                label = "Pedir Café",
                modifier = Modifier.weight(1f),
            )
            QuickActionTile(
                icon = { Icon(Icons.Default.Refresh, contentDescription = null, tint = LuminousBlueLight) },
                label = "Solicitar\nMáquina",
                modifier = Modifier.weight(1f),
            )
            QuickActionTile(
                icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = LuminousBlueLight) },
                label = "Ver\nCardápio",
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(Modifier.height(32.dp))
    }
}

// ── Private helpers ─────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        color = TextPrimary,
    )
}

@Composable
private fun QuickActionTile(
    icon: @Composable () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    CafeLavadoCard(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(DarkSurfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                icon()
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = TextPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
