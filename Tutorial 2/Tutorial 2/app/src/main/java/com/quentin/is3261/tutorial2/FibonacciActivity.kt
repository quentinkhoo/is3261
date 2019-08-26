package com.quentin.is3261.tutorial2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FibonacciActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci)

        val backButton = findViewById<Button>(R.id.fib_back)

        backButton.setOnClickListener {
            finish()
        }

        val fibonacciNonRecursiveButton = findViewById<Button>(R.id.fib_non_rec)

        fibonacciNonRecursiveButton.setOnClickListener {
            val fibonacciNonRecurseIntent = Intent(this, FibonacciNonRecursiveActivity::class.java)
            startActivity(fibonacciNonRecurseIntent)
        }

        val fibonacciRecursiveButton = findViewById<Button>(R.id.fib_rec)

        fibonacciRecursiveButton.setOnClickListener {
            val fibonacciRecurseIntent = Intent(this, FibonacciRecursiveActivity::class.java)
            startActivity(fibonacciRecurseIntent)
        }
    }
}
