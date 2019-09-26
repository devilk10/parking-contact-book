package com.example.parkingmanager.data

import androidx.lifecycle.LiveData
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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

    fun register(name: String, mobileNumber: String, vehicleNumber: String, vehicleType: String) {
        val person = Person(null, name, mobileNumber)
        GlobalScope.launch { addPersonBG(person) }
    }

    private suspend fun addPersonBG(person: Person) {
        withContext(Dispatchers.IO) {
            mDatabase.personDao().insert(person)
        }
    }

}
