package com.cafelavado.app.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cafelavado.app.components.CafeLavadoCard
import com.cafelavado.app.components.CardVariant
import com.cafelavado.app.components.MachineStatusCard
import com.cafelavado.app.components.PressableScale
import com.cafelavado.app.models.MachineStatus
import com.cafelavado.app.models.sampleMachines
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.CaramelBright
import com.cafelavado.app.theme.CaramelRose
import com.cafelavado.app.theme.CaramelSubtle
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.DarkSurfaceVariant
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

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

        // --- Hero header ---
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Caramel.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center,
            ) {
                Text("☕", style = MaterialTheme.typography.headlineMedium)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "CAFÉ LAVADO",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.5.sp,
                    ),
                    color = TextPrimary,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Café & Lavanderia · Bom dia, Maria",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // --- Loyalty hero card ---
        CafeLavadoCard(variant = CardVariant.Warm) {
            Text(
                text = "PROGRAMA DE FIDELIDADE",
                style = MaterialTheme.typography.labelMedium,
                color = CaramelBright.copy(alpha = 0.8f),
            )
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "42",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = TextPrimary,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "pontos · faltam 8 para café grátis",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 6.dp),
                )
            }
            Spacer(Modifier.height(12.dp))
            // Progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(DarkSurfaceVariant),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.84f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(CaramelBright),
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // --- Active machines ---
        SectionHeader("Minhas Máquinas")
        Spacer(Modifier.height(10.dp))

        val activeMachines = sampleMachines.filter { it.status == MachineStatus.OCCUPIED }
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(tween(300)),
            exit = fadeOut(tween(200)),
        ) {
            if (activeMachines.isEmpty()) {
                CafeLavadoCard {
                    Text(
                        text = "Nenhuma máquina ativa no momento.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    activeMachines.forEach { machine ->
                        MachineStatusCard(machine = machine)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // --- Quick actions ---
        SectionHeader("Ações Rápidas")
        Spacer(Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            QuickActionTile(
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = CaramelBright) },
                label = "Pedir\nCafé",
                modifier = Modifier.weight(1f),
            )
            QuickActionTile(
                icon = { Icon(Icons.Default.Refresh, contentDescription = null, tint = CaramelBright) },
                label = "Solicitar\nMáquina",
                modifier = Modifier.weight(1f),
            )
            QuickActionTile(
                icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = CaramelBright) },
                label = "Ver\nCardápio",
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(Modifier.height(32.dp))
    }
}

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
    PressableScale(onClick = {}, modifier = modifier) {
        CafeLavadoCard {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Caramel.copy(alpha = 0.18f)),
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
}
