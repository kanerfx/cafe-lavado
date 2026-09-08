package com.cafelavado.app.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ============================================================
//  WashingMachine
//  ------------------------------------------------------------
//  Firestore-ready: @Serializable + nullable optional fields with
//  defaults, so partial documents hydrate cleanly.
//
//  Suggested Firestore collection: `machines`
//  Document shape mirrors this data class (snake_case fields).
// ============================================================

@Serializable
data class WashingMachine(
    @SerialName("id")                    val id: String = "",
    @SerialName("label")                 val label: String = "",
    @SerialName("type")                  val type: MachineType = MachineType.WASH,
    @SerialName("status")                val status: MachineStatus = MachineStatus.FREE,
    @SerialName("time_remaining_seconds") val timeRemainingSeconds: Int? = null,
    @SerialName("cycle_started_at")      val cycleStartedAt: Long? = null,
    @SerialName("cycle_duration_seconds") val cycleDurationSeconds: Int? = null,
    @SerialName("reserved_by")           val reservedBy: String? = null,
    @SerialName("location")              val location: String? = null,
)

@Serializable
enum class MachineType(val displayName: String) {
    @SerialName("WASH") WASH("Lavadora"),
    @SerialName("DRY")  DRY("Secadora"),
}

@Serializable
enum class MachineStatus(val displayName: String) {
    @SerialName("FREE")      FREE("Livre"),
    @SerialName("OCCUPIED")  OCCUPIED("Ocupada"),
    @SerialName("RESERVED")  RESERVED("Reservada"),
}

val sampleMachines = listOf(
    WashingMachine("1", "Lavadora 01", MachineType.WASH, MachineStatus.FREE),
    WashingMachine("2", "Lavadora 02", MachineType.WASH, MachineStatus.OCCUPIED, timeRemainingSeconds = 1230),
    WashingMachine("3", "Lavadora 03", MachineType.WASH, MachineStatus.RESERVED),
    WashingMachine("4", "Lavadora 04", MachineType.WASH, MachineStatus.FREE),
    WashingMachine("5", "Secadora 01", MachineType.DRY,  MachineStatus.FREE),
    WashingMachine("6", "Secadora 02", MachineType.DRY,  MachineStatus.OCCUPIED, timeRemainingSeconds = 2400),
    WashingMachine("7", "Secadora 03", MachineType.DRY,  MachineStatus.FREE),
)
