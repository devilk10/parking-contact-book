package com.example.parkingmanager.data

import android.os.AsyncTask
import android.util.Log
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person
import com.example.parkingmanager.database.entity.Vehicle
import java.io.IOException


/**
 * Class that handles authentication w/ register credentials and retrieves user information.
 */
class VehicleDataSource(database: VehicleDatabase) {

    private val TAG = "VehicleData"
    private val personDao = database.personDao()
    private val vehicleDao = database.vehicleDao()


    fun register(
        name: String,
        vehicleNumber: String,
        mobileNumber: String,
        vehicleType: String
    ): Result<Boolean> {
        var result: Result<Boolean> = Result.Error(IllegalStateException())

        AsyncTask.execute {
            result = try {
                val person = Person(null, name, mobileNumber)
                personDao.insert(person = person)
                val id = personDao.findPerson(name, mobileNumber).id
                val vehicle = Vehicle(id, vehicleType, vehicleNumber)
                vehicleDao.insert(vehicle)

                Result.Success(true)
            } catch (e: Throwable) {
                Log.d(TAG, e.localizedMessage)
                Result.Error(IOException("Error ", e))
            }
        }
        return result
    }
}

