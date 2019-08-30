package com.example.parkingmanager.ui.registration

/**
 * Authentication result : success (user details) or error message.
 */
data class RegistrationResult(
    val success: Boolean? = null,
    val error: Int? = null
)
