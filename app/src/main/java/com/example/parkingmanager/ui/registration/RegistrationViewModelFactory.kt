package com.example.parkingmanager.ui.registration

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.parkingmanager.data.VehicleDataSource
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
                    dataSource = VehicleDataSource(database = vehicleData)
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
