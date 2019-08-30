package com.example.parkingmanager.ui.registration

/**
 * Data validation state of the register form.
 */
data class RegistrationFormState(
    val nameError: Int? = null,
    val mobileNumberError: Int? = null,
    val vehicleNumberError: Int? = null,
    val isDataValid: Boolean = false
)
