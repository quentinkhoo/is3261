package com.quentin.is3261.tutorial2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FibonacciRecursiveActivity : AppCompatActivity() {

    internal var fibRecurseResults: String = ""
    internal var fibArray = IntArray(1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci_recursive)

        fibArray.set(1, 1)

        var inputValue = findViewById<EditText>(R.id.fib_rec_input)
        var fibRecurseResultsTextView = findViewById<TextView>(R.id.fib_recurse_results)
        fibRecurseResultsTextView.setText(fibRecurseResults)

        val generateButton = findViewById<Button>(R.id.fib_rec_gen_butt)
        generateButton.setOnClickListener {
            fibRecurseResults = ""
            fibonacciRecursive(inputValue.text.toString().toInt())
            generateFibonacciResultsToUser(inputValue.text.toString().toInt())
            fibRecurseResultsTextView.setText(fibRecurseResults)

        }

        val backButton = findViewById<Button>(R.id.fib_rec_back)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun generateFibonacciResultsToUser(userInput: Int) {
        for (i in 1..userInput) {
            if (i != userInput) {
                fibRecurseResults += fibArray[i].toString() + ", "
            } else {
                fibRecurseResults += fibArray[i].toString()
            }
        }
    }

    private fun fibonacciRecursive(num: Int) : Int {
        if (num <= 1) {
            return num
        } else if (fibArray[num] != 0) {
            return fibArray[num]
        } else {
            fibArray[num] = fibonacciRecursive(num-1) + fibonacciRecursive(num-2)
            return fibArray[num]
        }
    }

}
