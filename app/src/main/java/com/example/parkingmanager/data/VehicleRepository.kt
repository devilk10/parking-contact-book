package com.example.parkingmanager.data

import androidx.lifecycle.LiveData
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of register status and user credentials information.
 */

class VehicleRepository(val mDatabase: VehicleDatabase) {

    companion object {
        private fun getInstance(mDatabase: VehicleDatabase): VehicleRepository? {
            var sInstance: VehicleRepository? = null
            val LOCK = Any()

            sInstance ?: synchronized(LOCK) {
                sInstance ?: VehicleRepository(mDatabase)!!.also { sInstance = it }
            }
            return sInstance
        }
    }

    fun getAllPeople(): LiveData<List<Person>> {
        return mDatabase.personDao().getAll()
    }

    fun register(name: String, mobileNumber: String, vehicleNumber: String, vehicleType: String): Boolean {
        return true
    }

}
