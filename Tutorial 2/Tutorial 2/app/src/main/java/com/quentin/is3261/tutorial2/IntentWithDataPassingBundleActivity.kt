package com.quentin.is3261.tutorial2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class IntentWithDataPassingBundleActivity : AppCompatActivity() {

    internal var toastMessageBuilder = StringBuilder()
    internal var country : String = ""
    internal var sports : String = ""
    internal var teamSport : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_with_data_passing_bundle)

        val backbutton = findViewById<Button>(R.id.intent_pass_data_bundle_back_btn)
        backbutton.setOnClickListener {
            finish()
        }

        country = intent.getStringExtra("Country")
        sports = intent.getStringExtra("Sports")
        teamSport = intent.getBooleanExtra("Team Sport", false)
        toastMessageBuilder()

        Toast.makeText(this, toastMessageBuilder.toString(), Toast.LENGTH_LONG).show()
    }

    private fun toastMessageBuilder() {
        toastMessageBuilder.append("Data passed from calling activity: ")
        toastMessageBuilder.append("country = ")
        toastMessageBuilder.append(country)
        toastMessageBuilder.append("  sports = ")
        toastMessageBuilder.append(sports)
        toastMessageBuilder.append("  team sport = ")
        toastMessageBuilder.append(teamSport)
    }
}
