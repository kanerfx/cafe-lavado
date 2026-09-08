package com.cafelavado.app.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ============================================================
//  UserProfile
//  ------------------------------------------------------------
//  Suggested Firestore collection: `users/{uid}`
// ============================================================

@Serializable
data class UserProfile(
    @SerialName("id")             val id: String = "",
    @SerialName("name")           val name: String = "",
    @SerialName("email")          val email: String = "",
    @SerialName("loyalty_points") val loyaltyPoints: Int = 0,
    @SerialName("avatar_url")     val avatarUrl: String? = null,
    @SerialName("phone")          val phone: String? = null,
    @SerialName("created_at")     val createdAt: Long? = null,
)

val sampleProfile = UserProfile(
    id = "usr_001",
    name = "Maria Silva",
    email = "maria@email.com",
    loyaltyPoints = 42,
)
