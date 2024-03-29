package com.example.parkingmanager.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingmanager.R
import com.example.parkingmanager.data.VehicleRepository
import com.example.parkingmanager.database.entity.Person

class VehicleViewModel(private val vehicleRepository: VehicleRepository) : ViewModel() {

    private val _registrationForm = MutableLiveData<RegistrationFormState>()
    val registrationFormState: LiveData<RegistrationFormState> = _registrationForm

    fun getPeople(): LiveData<List<Person>> {
        return vehicleRepository.getAllPeople()
    }

    fun register(
        name: String,
        mobileNumber: String,
        vehicleNumber: String,
        vehicleType: String
    ) {
        vehicleRepository.register(name, mobileNumber, vehicleNumber, vehicleType)
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
        return true
//        return number.length > 9
    }

    private fun isVehicleNumberValid(number: String): Boolean {
        return true
//        val regex = """^[A-Z]{2}\s[0-9]{1,2}\s[A-Z]{1,2}\s[0-9]{1,4}${'$'}""".toRegex()
//        return regex.containsMatchIn(input = number)
    }

    fun filterPeople(filterText: String, people: List<Person>): List<Person> {
        return people.filter { it.name.contains(filterText) }
    }
}
