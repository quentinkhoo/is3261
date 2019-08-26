package com.quentin.is3261.tutorial3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ActivityInterface : AppCompatActivity(), Fragment3.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface)

        val interfaceActivityBackButton = findViewById<Button>(R.id.interface_activity_back_butt)
        interfaceActivityBackButton.setOnClickListener {
            finish()
        }
    }

    override fun onFragmentInteraction(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
}
