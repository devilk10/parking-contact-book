package com.example.parkingmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingmanager.adapter.PeopleAdapter
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.database.entity.Person
import com.example.parkingmanager.ui.registration.RegistrationViewModelFactory
import com.example.parkingmanager.ui.registration.VehicleViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var allPeople: List<Person>
    private val TAG = "Parking"

    private lateinit var vehicleViewModel: VehicleViewModel
    private lateinit var mMaterialSearchView: MaterialSearchView
    lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val database: VehicleDatabase = VehicleDatabase.getDatabase(this)

        mMaterialSearchView = findViewById(R.id.search_view)
        vehicleViewModel = ViewModelProviders.of(this, RegistrationViewModelFactory(database))
            .get(VehicleViewModel::class.java)

        vehicleViewModel.getPeople().observe(this, Observer<List<Person>> {
            allPeople = it
            this.renderIt(it)
        })

        val layoutManager = LinearLayoutManager(this)
        messageRecyclerView.layoutManager = layoutManager

        registerPageWay()
        setupSearchBar()
    }

    private fun registerPageWay() {
        fab.setOnClickListener {
            val intent = Intent(this, VehicleActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        mMaterialSearchView.setMenuItem(item)

        return true
    }

    private fun renderIt(people: List<Person>?) {
        val peopleAlphabetically = people?.sortedBy { it.name.toLowerCase() }
        peopleAlphabetically?.forEach {
            Log.d(TAG, it.toString())
        }
        adapter = PeopleAdapter(peopleAlphabetically)
        messageRecyclerView.adapter = adapter
    }

    private fun setupSearchBar() {
        mMaterialSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d(TAG, "onQuerySubmit" + query)
                //Do some magic
                return false
            }

            override fun onQueryTextChange(filterText: String): Boolean {
                Log.d(TAG, "onQuerySubmit" + filterText)
                val filterPeople = vehicleViewModel.filterPeople(filterText, allPeople)
                renderIt(filterPeople)
                return false
            }
        })

        mMaterialSearchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                Log.d(TAG, "onQuerySubmit search view shown")
                //Do some magic
            }

            override fun onSearchViewClosed() {
                //Do some magic
                renderIt(allPeople)
                Log.d(TAG, "onQuerySubmit search view closed")
            }
        })
    }
}
