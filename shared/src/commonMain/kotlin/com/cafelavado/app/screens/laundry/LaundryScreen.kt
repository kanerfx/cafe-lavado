package com.cafelavado.app.screens.laundry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cafelavado.app.components.CafeLavadoCard
import com.cafelavado.app.components.CardVariant
import com.cafelavado.app.components.MachineStatusCard
import com.cafelavado.app.models.MachineStatus
import com.cafelavado.app.models.sampleMachines
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.StatusFree
import com.cafelavado.app.theme.StatusOccupied
import com.cafelavado.app.theme.StatusReserved
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

@Composable
fun LaundryScreen() {
    val freeCount     = sampleMachines.count { it.status == MachineStatus.FREE }
    val occupiedCount = sampleMachines.count { it.status == MachineStatus.OCCUPIED }
    val reservedCount = sampleMachines.count { it.status == MachineStatus.RESERVED }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Lavanderia",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Status em tempo real das máquinas",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        Spacer(Modifier.height(16.dp))

        // --- Status counters card ---
        CafeLavadoCard(
            variant = CardVariant.Warm,
            modifier = Modifier.padding(horizontal = 20.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
            ) {
                StatusCounter(count = freeCount,     label = "Livres",     color = StatusFree)
                StatusCounter(count = occupiedCount, label = "Ocupadas",   color = StatusOccupied)
                StatusCounter(count = reservedCount, label = "Reservadas", color = StatusReserved)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(
            text = "Todas as Máquinas",
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        Spacer(Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
        ) {
            items(sampleMachines, key = { it.id }) { machine ->
                MachineStatusCard(machine = machine)
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun StatusCounter(
    count: Int,
    label: String,
    color: androidx.compose.ui.graphics.Color,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = color,
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary,
        )
    }
}
