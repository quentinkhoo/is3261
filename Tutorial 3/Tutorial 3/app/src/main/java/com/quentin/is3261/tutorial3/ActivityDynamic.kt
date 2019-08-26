package com.quentin.is3261.tutorial3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityDynamic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val fragment1 = Fragment1()
        val fragment2 = Fragment2()

        transaction.add(R.id.frameLayout1, fragment1)
        transaction.add(R.id.frameLayout2, fragment2)

        transaction.commit()

        val dynamicActivityBackButton = findViewById<Button>(R.id.dynamic_activity_back_butt)
        dynamicActivityBackButton.setOnClickListener {
            finish()
        }
    }
}
