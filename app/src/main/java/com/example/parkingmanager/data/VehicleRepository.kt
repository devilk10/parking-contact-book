package com.example.parkingmanager.data

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of register status and user credentials information.
 */

class VehicleRepository(val dataSource: VehicleDataSource) {

    fun register(name: String, mobileNumber: String, vehicleNumber: String): Boolean {
        // handle register
        val result = dataSource.register(name, mobileNumber, vehicleNumber)

        if (result is Result.Success) {
            //TODO: action after registration
            return true
        }
        return false
    }

}
