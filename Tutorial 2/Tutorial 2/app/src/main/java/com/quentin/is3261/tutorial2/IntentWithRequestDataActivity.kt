package com.quentin.is3261.tutorial2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IntentWithRequestDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_with_request_data)

        val backBtn = findViewById<Button>(R.id.request_data_back_btn)

        backBtn.setOnClickListener {
            val myIntent = Intent()
            myIntent.putExtra("Continent", "Asia")
            setResult(Activity.RESULT_OK, myIntent)
            finish()
        }
    }
}
