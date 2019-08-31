package com.example.parkingmanager.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

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