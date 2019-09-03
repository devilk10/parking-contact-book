package com.example.parkingmanager.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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