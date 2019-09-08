package com.example.parkingmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.parkingmanager.database.entity.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): LiveData<List<Person>>

    @Query("SELECT * FROM person WHERE name = :name and mobile_number = :mobileNumber")
    fun findPerson(name: String, mobileNumber: String): LiveData<Person>

    @Query("SELECT * FROM person WHERE name LIKE :name")
    fun findPersonALike(name: String): List<Person>

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}