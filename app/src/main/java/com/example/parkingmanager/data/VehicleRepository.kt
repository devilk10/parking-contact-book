package com.example.parkingmanager.data

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of register status and user credentials information.
 */

class VehicleRepository(val dataSource: VehicleDataSource) {

    fun register(
        name: String,
        mobileNumber: String,
        vehicleNumber: String,
        vehicleType: String
    ): Boolean {
        val result = dataSource.register(name, mobileNumber, vehicleNumber, vehicleType)
        return result is Result.Success
    }

}
