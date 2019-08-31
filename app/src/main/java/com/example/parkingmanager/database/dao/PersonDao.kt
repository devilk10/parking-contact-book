package com.example.parkingmanager.database.dao

import android.arch.persistence.room.*
import com.example.parkingmanager.database.entity.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE name = :name and mobile_number = :mobileNumber")
    fun findPerson(name: String, mobileNumber: String): Person

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}