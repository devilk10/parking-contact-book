package com.example.parkingmanager.ui.registration

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.parkingmanager.data.VehicleDataSource
import com.example.parkingmanager.data.VehicleRepository

/**
 * ViewModel provider factory to instantiate VehicleViewModel.
 * Required given VehicleViewModel has a non-empty constructor
 */
class RegistrationViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            return VehicleViewModel(
                vehicleRepository = VehicleRepository(
                    dataSource = VehicleDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
