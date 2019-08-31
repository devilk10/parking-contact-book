package com.example.parkingmanager.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.parkingmanager.database.dao.PersonDao
import com.example.parkingmanager.database.entity.Person

@Database(entities = arrayOf(Person::class), version = 1)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var instance: VehicleDatabase? = null
        private val LOCK = Any()

        fun getDatabase(context: Context): VehicleDatabase = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            VehicleDatabase::class.java, "vehicle_data.db"
        )
            .build()
    }
}