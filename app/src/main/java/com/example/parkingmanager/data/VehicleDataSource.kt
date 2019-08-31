package com.example.parkingmanager.data

import android.os.AsyncTask
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person
import java.io.IOException


/**
 * Class that handles authentication w/ register credentials and retrieves user information.
 */
class VehicleDataSource(database: VehicleDatabase) {

    private val personDao = database.personDao()


    fun register(name: String, mobileNumber: String, vehicleNumber: String): Result<Boolean> {
        var result: Result<Boolean> = Result.Success(false)

        AsyncTask.execute {
            result = try {
                val person = Person(null, name, mobileNumber)
                personDao.insert(person = person)
                Result.Success(true)
            } catch (e: Throwable) {
                Result.Error(IOException("Error ", e))
            }
        }
        return result
    }
}

