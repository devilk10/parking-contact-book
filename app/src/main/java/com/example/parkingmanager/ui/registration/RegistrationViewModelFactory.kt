package com.example.parkingmanager.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parkingmanager.data.VehicleRepository
import com.example.parkingmanager.database.VehicleDatabase

/**
 * ViewModel provider factory to instantiate VehicleViewModel.
 * Required given VehicleViewModel has a non-empty constructor
 */
class RegistrationViewModelFactory(private val vehicleData: VehicleDatabase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            return VehicleViewModel(
                vehicleRepository = VehicleRepository(
                    mDatabase = vehicleData
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
