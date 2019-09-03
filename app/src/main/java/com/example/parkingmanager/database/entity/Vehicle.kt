package com.example.parkingmanager.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle")
data class Vehicle(
    @ColumnInfo(name = "person_id")
    @ForeignKey(entity = Person::class, parentColumns = arrayOf("id"), childColumns = arrayOf("person_id"))
    val personId: Int? = null,

    @ColumnInfo(name = "vehicle_type")
    var vehicleType: String,

    @PrimaryKey @ColumnInfo(name = "vehicle_number")
    var vehicleNumber: String

)