package com.example.parkingmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingmanager.adapter.PeopleAdapter
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person
import com.example.parkingmanager.ui.registration.RegistrationViewModelFactory
import com.example.parkingmanager.ui.registration.VehicleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Parking"

    private lateinit var vehicleViewModel: VehicleViewModel

    lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val database: VehicleDatabase = VehicleDatabase.getDatabase(this)

        vehicleViewModel = ViewModelProviders.of(this, RegistrationViewModelFactory(database))
            .get(VehicleViewModel::class.java)

        vehicleViewModel?.getPeople()?.observe(this, Observer<List<Person>> { this.renderIt(it) })

        val layoutManager = LinearLayoutManager(this)
        messageRecyclerView.layoutManager = layoutManager

        fab.setOnClickListener {
            val intent = Intent(this, VehicleActivity::class.java)
            startActivity(intent)
        }
    }

    private fun renderIt(people: List<Person>?) {
        val peopleAlphabetically = people?.sortedBy { it.name.toLowerCase() }
        peopleAlphabetically?.forEach {
            Log.d(TAG, it.toString())
        }
        adapter = PeopleAdapter(peopleAlphabetically)
        messageRecyclerView.adapter = adapter
    }

}
