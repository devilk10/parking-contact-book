package com.example.parkingmanager.database.dao

import android.arch.persistence.room.*
import com.example.parkingmanager.database.entity.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE name = :name")
    fun findPerson(name: String): Person

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}