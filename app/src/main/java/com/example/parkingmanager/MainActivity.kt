package com.example.parkingmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person
import com.example.parkingmanager.ui.registration.RegistrationViewModelFactory
import com.example.parkingmanager.ui.registration.VehicleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var vehicleViewModel: VehicleViewModel

    private val TAG = "People"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val database: VehicleDatabase = VehicleDatabase.getDatabase(this)

        vehicleViewModel = ViewModelProviders.of(this, RegistrationViewModelFactory(database))
            .get(VehicleViewModel::class.java)

        vehicleViewModel?.getPeople()?.observe(this, Observer<List<Person>> { this.showIt(it) })


        fab.setOnClickListener {
            val intent = Intent(this, VehicleActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showIt(people: List<Person>?) {
        people?.forEach { Log.d(TAG, it.toString()) }
    }

}
