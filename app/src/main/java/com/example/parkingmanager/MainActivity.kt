package com.example.parkingmanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mMaterialSearchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mMaterialSearchView = findViewById(R.id.search_view)

        fab.setOnClickListener { view ->
            val intent = Intent(this, VehicleActivity::class.java)
            startActivity(intent)
        }

        mMaterialSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Do some magic
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //Do some magic
                return false
            }
        })

        mMaterialSearchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                //Do some magic
            }

            override fun onSearchViewClosed() {
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
