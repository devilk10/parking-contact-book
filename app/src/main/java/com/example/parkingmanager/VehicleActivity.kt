package com.example.parkingmanager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parkingmanager.database.VehicleDatabase
import com.example.parkingmanager.ui.registration.RegistrationViewModelFactory
import com.example.parkingmanager.ui.registration.VehicleViewModel
import kotlinx.android.synthetic.main.activity_vehicle.*

class VehicleActivity : AppCompatActivity() {

    private lateinit var vehicleViewModel: VehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_vehicle)

        val name = findViewById<EditText>(R.id.name)
        val vehicleNumber = findViewById<EditText>(R.id.vehicleNumber)
        val vehicleType = findViewById<Spinner>(R.id.vehicle_type)
        val mobileNumber = findViewById<EditText>(R.id.mobileNumber)
        val registerButton = findViewById<Button>(R.id.add)

        val database: VehicleDatabase = VehicleDatabase.getDatabase(this)

        vehicleViewModel = ViewModelProviders.of(this, RegistrationViewModelFactory(database))
            .get(VehicleViewModel::class.java)

        vehicleViewModel.registrationFormState.observe(this@VehicleActivity, Observer {
            val registrationState = it ?: return@Observer

//             disable add button unless all the fields are valid
            registerButton.isEnabled = registrationState.isDataValid

            if (registrationState.nameError != null) {
                name.error = getString(registrationState.nameError)
            }
            if (registrationState.vehicleNumberError != null) {
                vehicleNumber.error = getString(registrationState.vehicleNumberError)
            }
            if (registrationState.mobileNumberError != null) {
                mobileNumber.error = getString(registrationState.mobileNumberError)
            }
        })

        name.afterTextChanged {
            notifyChange()
        }

        mobileNumber.afterTextChanged {
            notifyChange()
        }

        vehicleNumber.afterTextChanged {
            notifyChange()
        }

        registerButton.setOnClickListener {
            vehicleViewModel.register(
                name.text.toString(),
                vehicleNumber.text.toString(),
                mobileNumber.text.toString(),
                vehicleType.selectedItem.toString()
            )

            clearTextBox()
        }
    }

    private fun clearTextBox() {
        name.text.clear()
        vehicleNumber.text.clear()
        mobileNumber.text.clear()
    }

    private fun notifyChange() {
        vehicleViewModel.registrationDataChanged(
            name.text.toString(),
            mobileNumber.text.toString(),
            vehicleNumber.text.toString()
        )
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}