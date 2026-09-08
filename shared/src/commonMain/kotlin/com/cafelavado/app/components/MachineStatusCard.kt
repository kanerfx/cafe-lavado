package com.cafelavado.app.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cafelavado.app.models.MachineStatus
import com.cafelavado.app.models.WashingMachine
import com.cafelavado.app.theme.Caramel
import com.cafelavado.app.theme.CaramelBright
import com.cafelavado.app.theme.CaramelRose
import com.cafelavado.app.theme.CaramelSubtle
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.DarkSurfaceVariant
import com.cafelavado.app.theme.StatusFree
import com.cafelavado.app.theme.StatusOccupied
import com.cafelavado.app.theme.StatusReserved
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

// ============================================================
//  MachineStatusCard — animated machine row with pulsing status dot
//  and color-tinted CTA button.
// ============================================================

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
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            StatusDot(
                color = statusColor,
                size = 12.dp,
                pulse = machine.status == MachineStatus.OCCUPIED,
            )

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
                    AnimatedContent(
                        targetState = formatTimer(machine.timeRemainingSeconds),
                        transitionSpec = {
                            (slideInVertically(tween(200)) { it / 4 } + fadeIn(tween(200))) togetherWith
                                (slideOutVertically(tween(200)) { -it / 4 } + fadeOut(tween(200)))
                        },
                        label = "timer-anim",
                    ) { timer ->
                        Text(
                            text = timer,
                            style = MaterialTheme.typography.titleSmall,
                            color = CaramelBright,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }

            Button(
                onClick = { onAction(machine) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Caramel,
                    contentColor = DarkBackground,
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(actionLabel, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

private fun formatTimer(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
