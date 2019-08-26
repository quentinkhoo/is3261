package com.quentin.is3261.tutorial3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStaticFragment = findViewById<Button>(R.id.static_fragment_butt)
        btnStaticFragment.setOnClickListener {
            val myIntent = Intent(this, ActivityStatic::class.java)
            startActivity(myIntent)
        }


        val btnDynamicFragment = findViewById<Button>(R.id.dynamic_fragment_butt)
        btnDynamicFragment.setOnClickListener {
            val myIntent = Intent(this, ActivityDynamic::class.java)
            startActivity(myIntent)
        }

        val btnfragmentWithInterface = findViewById<Button>(R.id.fragment_with_interface_butt)
        btnfragmentWithInterface.setOnClickListener {
            val myIntent = Intent(this, ActivityInterface::class.java)
            startActivity(myIntent)
        }

    }
}
