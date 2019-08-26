package com.quentin.is3261.tutorial2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal val REQUEST_CODE: Int = 1
    internal var continentMessage: String = ""
    internal var toastMessageBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fibonacciButton = findViewById<Button>(R.id.fib_butt)

        fibonacciButton.setOnClickListener {
            val myIntent = Intent(this, FibonacciActivity::class.java)
            startActivity(myIntent)
        }

        val intentWithDataPassingButton = findViewById<Button>(R.id.intent1_butt)

        intentWithDataPassingButton.setOnClickListener {
            val myIntent = Intent(this, IntentWithDataPassingActivity::class.java)
            myIntent.putExtra("Country", "Singapore")
            myIntent.putExtra("Sports", "Football")
            myIntent.putExtra("Team Size", 11)
            startActivity(myIntent)
        }

        val intentWithDataPassingBundleButton = findViewById<Button>(R.id.intent2_butt)

        intentWithDataPassingBundleButton.setOnClickListener {
            val myIntent = Intent(this, IntentWithDataPassingBundleActivity::class.java)
            var bundle = Bundle()

            bundle.putString("Country", "Indonesia")
            bundle.putString("Sports", "Badminton")
            bundle.putBoolean("Team Size", true)

            myIntent.putExtras(bundle)

            startActivity(myIntent)
        }

        val intentWithRequestDataButton = findViewById<Button>(R.id.intent3_butt)

        intentWithRequestDataButton.setOnClickListener {
            val myIntent = Intent(this, IntentWithRequestDataActivity::class.java)
            startActivityForResult(myIntent, REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data.hasExtra("Continent")) {
                continentMessage = data.extras.getString("Continent")
                buildToastMessage()
                Toast.makeText(this, toastMessageBuilder.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun buildToastMessage() {
        toastMessageBuilder = StringBuilder()
        toastMessageBuilder.append("Returned Continent = ")
        toastMessageBuilder.append(continentMessage)
    }
}
