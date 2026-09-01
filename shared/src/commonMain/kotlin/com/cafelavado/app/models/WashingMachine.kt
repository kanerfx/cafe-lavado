package com.cafelavado.app.models

/**
 * Represents a washing or drying machine in the laundry room.
 *
 * @param timeRemainingSeconds Seconds left on the cycle, `null` when idle / reserved.
 */
data class WashingMachine(
    val id: String,
    val label: String,
    val type: MachineType,
    val status: MachineStatus,
    val timeRemainingSeconds: Int? = null,
)

enum class MachineType(val displayName: String) {
    WASH("Lavadora"),
    DRY("Secadora"),
}

enum class MachineStatus(val displayName: String) {
    FREE("Livre"),
    OCCUPIED("Ocupada"),
    RESERVED("Reservada"),
}

/** Placeholder data for the laundry foundation. */
val sampleMachines = listOf(
    WashingMachine("1", "Lavadora 01", MachineType.WASH, MachineStatus.FREE),
    WashingMachine("2", "Lavadora 02", MachineType.WASH, MachineStatus.OCCUPIED, timeRemainingSeconds = 1230),
    WashingMachine("3", "Lavadora 03", MachineType.WASH, MachineStatus.RESERVED),
    WashingMachine("4", "Lavadora 04", MachineType.WASH, MachineStatus.FREE),
    WashingMachine("5", "Secadora 01", MachineType.DRY,  MachineStatus.FREE),
    WashingMachine("6", "Secadora 02", MachineType.DRY,  MachineStatus.OCCUPIED, timeRemainingSeconds = 2400),
    WashingMachine("7", "Secadora 03", MachineType.DRY,  MachineStatus.FREE),
)
