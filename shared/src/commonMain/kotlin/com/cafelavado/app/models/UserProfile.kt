package com.cafelavado.app.models

/**
 * Represents the current user's profile.
 */
data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val loyaltyPoints: Int = 0,
)

/** Placeholder profile for the foundation. */
val sampleProfile = UserProfile(
    id = "usr_001",
    name = "Maria Silva",
    email = "maria@email.com",
    loyaltyPoints = 42,
)
