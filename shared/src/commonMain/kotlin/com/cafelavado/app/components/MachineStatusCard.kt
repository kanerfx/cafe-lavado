package com.cafelavado.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cafelavado.app.models.MachineStatus
import com.cafelavado.app.models.WashingMachine
import com.cafelavado.app.theme.*

/**
 * Displays a washing/drying machine's current status with
 * a colour-coded indicator, countdown timer, and action button.
 */
@Composable
fun MachineStatusCard(
    machine: WashingMachine,
    onAction: (WashingMachine) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val statusColor = when (machine.status) {
        MachineStatus.FREE     -> StatusFree
        MachineStatus.OCCUPIED -> StatusOccupied
        MachineStatus.RESERVED -> StatusReserved
    }

    val actionLabel = when (machine.status) {
        MachineStatus.FREE     -> "Reservar"
        MachineStatus.OCCUPIED -> "Acompanhar"
        MachineStatus.RESERVED -> "Cancelar"
    }

    CafeLavadoCard(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Status dot
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(statusColor),
            )

            Spacer(Modifier.width(12.dp))

            // Machine info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = machine.label,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary,
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${machine.type.displayName} · ${machine.status.displayName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                )
                if (machine.timeRemainingSeconds != null) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = formatTimer(machine.timeRemainingSeconds),
                        style = MaterialTheme.typography.titleSmall,
                        color = LuminousBlueLight,
                    )
                }
            }

            // Action button
            Button(
                onClick = { onAction(machine) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LuminousBlue,
                    contentColor = TextPrimary,
                ),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(actionLabel, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

/** Formats seconds into "MM:SS". */
private fun formatTimer(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
