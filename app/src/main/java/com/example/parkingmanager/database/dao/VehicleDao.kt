package com.example.parkingmanager.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.parkingmanager.database.entity.Vehicle

@Dao
interface VehicleDao {
    @Query("SELECT * FROM vehicle")
    fun getAll(): List<Vehicle>

    @Query("SELECT * FROM vehicle WHERE vehicle_number = :vehicleNumber")
    fun findVehicle(vehicleNumber: String): Vehicle

    @Insert
    fun insert(vehicle: Vehicle)

    @Delete
    fun delete(vehicle: Vehicle)
}