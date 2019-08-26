package com.quentin.is3261.tutorial2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class IntentWithDataPassingActivity : AppCompatActivity() {

    internal var toastMessageBuilder = StringBuilder()
    internal var country : String = ""
    internal var sports : String = ""
    internal var teamSize : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_with_passing_data)

        country = intent.getStringExtra("Country")
        sports = intent.getStringExtra("Sports")
        teamSize = intent.getIntExtra("Team Size", 0)
        buildToastMessage()

        Toast.makeText(this, toastMessageBuilder.toString(), Toast.LENGTH_LONG).show()

        val backButton = findViewById<Button>(R.id.intent_pass_data_back_btn)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun buildToastMessage(){
        toastMessageBuilder.append("Data passed from calling activity: ")
        toastMessageBuilder.append("country = ")
        toastMessageBuilder.append(country)
        toastMessageBuilder.append("  sports = ")
        toastMessageBuilder.append(sports)
        toastMessageBuilder.append("  team size = ")
        toastMessageBuilder.append(teamSize)
    }
}
