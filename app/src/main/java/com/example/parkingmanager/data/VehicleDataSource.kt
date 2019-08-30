package com.example.parkingmanager.data

import java.io.IOException

/**
 * Class that handles authentication w/ register credentials and retrieves user information.
 */
class VehicleDataSource {

    fun register(name: String, mobileNumber: String, vehicleNumber: String): Result<Boolean> {
        try {
            // TODO: handle loggedInUser authentication
//            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(true)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error ", e))
        }
    }

}

