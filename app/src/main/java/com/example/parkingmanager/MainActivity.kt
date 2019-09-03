package com.example.parkingmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.ui.registration.RegistrationViewModelFactory
import com.example.parkingmanager.ui.registration.VehicleViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mMaterialSearchView: MaterialSearchView
    private lateinit var searchString: String
    private lateinit var vehicleViewModel: VehicleViewModel

    private val TAG = "Search string"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mMaterialSearchView = findViewById(R.id.search_view)
        val database: VehicleDatabase = VehicleDatabase.getDatabase(this)

        vehicleViewModel = ViewModelProviders.of(this, RegistrationViewModelFactory(database))
            .get(VehicleViewModel::class.java)

        fab.setOnClickListener { _ ->
            val intent = Intent(this, VehicleActivity::class.java)
            startActivity(intent)
        }

        mMaterialSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d(TAG, "final search is $query")
                //Do some magic
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                Log.d(TAG, "changed text is $query")
                searchString = query
//                val searchPerson = vehicleViewModel.searchPerson(query)
//                searchPerson.forEach {
//                    Log.d(TAG, it.toString())
//                }
                //Do some magic
                return false
            }
        })

        mMaterialSearchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                //Do some magic
            }

            override fun onSearchViewClosed() {
                Log.d(TAG, "search view closed")
                //Do some magic
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        mMaterialSearchView.setMenuItem(item)

        return true
    }


}
