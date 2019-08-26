package com.quentin.is3261.tutorial3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityStatic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static)

        val staticActivityBackButton = findViewById<Button>(R.id.static_activity_back_butt)
        staticActivityBackButton.setOnClickListener {
            finish()
        }
    }
}
