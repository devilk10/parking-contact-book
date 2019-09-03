package com.example.parkingmanager.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingmanager.R
import com.example.parkingmanager.data.VehicleRepository

class VehicleViewModel(private val vehicleRepository: VehicleRepository) : ViewModel() {

    private val _registrationForm = MutableLiveData<RegistrationFormState>()
    val registrationFormState: LiveData<RegistrationFormState> = _registrationForm

    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult

    fun register(
        name: String,
        mobileNumber: String,
        vehicleNumber: String,
        vehicleType: String
    ) {
        // can be launched in a separate asynchronous job
        val result = vehicleRepository.register(name, mobileNumber, vehicleNumber, vehicleType)
        if (result) {
            _registrationResult.value = RegistrationResult(success = true)
        } else {
            _registrationResult.value = RegistrationResult(error = R.string.registration_failed)
        }
    }

    fun registrationDataChanged(name: String, mobileNumber: String, vehicleNumber: String) {
        if (name.isBlank()) {
            _registrationForm.value = RegistrationFormState(nameError = R.string.invalid_name)
        } else if (!isMobileNumberValid(mobileNumber)) {
            _registrationForm.value = RegistrationFormState(mobileNumberError = R.string.invalid_contact)
        } else if (!isVehicleNumberValid(vehicleNumber)) {
            _registrationForm.value = RegistrationFormState(vehicleNumberError = R.string.invalid_vehicle)
        } else {
            _registrationForm.value = RegistrationFormState(isDataValid = true)
        }
    }

    private fun isMobileNumberValid(number: String): Boolean {
        return number.length > 9
    }

    private fun isVehicleNumberValid(number: String): Boolean {
        val regex = """^[A-Z]{2}\s[0-9]{1,2}\s[A-Z]{1,2}\s[0-9]{1,4}${'$'}""".toRegex()
        return regex.containsMatchIn(input = number)
    }
}
