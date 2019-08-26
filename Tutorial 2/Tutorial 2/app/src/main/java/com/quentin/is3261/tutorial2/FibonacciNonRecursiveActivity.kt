package com.quentin.is3261.tutorial2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FibonacciNonRecursiveActivity : AppCompatActivity() {

    internal var fibNonRecurseResults: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci_non_recursive)

        var inputValue = findViewById<EditText>(R.id.fib_non_rec_input)
        var fibNonRecurseResultsTextView = findViewById<TextView>(R.id.fib_non_recurse_results)
        fibNonRecurseResultsTextView.setText(fibNonRecurseResults)

        val generateButton = findViewById<Button>(R.id.fib_non_rec_gen_butt)
        generateButton.setOnClickListener {
            fibNonRecurseResults = ""
            fibonacciNonRecursive(inputValue.text.toString().toInt())
            fibNonRecurseResultsTextView.setText(fibNonRecurseResults)

        }

        val backButton = findViewById<Button>(R.id.fib_non_rec_back)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun fibonacciNonRecursive(num: Int){
        var fibPrevPrev = 1
        var fibPrev = 1

        for (i in 1..num) {
            if (i != num) {
                fibNonRecurseResults += fibPrevPrev.toString() + ", "

                val fibCurr = fibPrevPrev + fibPrev
                fibPrevPrev = fibPrev
                fibPrev = fibCurr
            } else {
                fibNonRecurseResults += fibPrevPrev.toString()

                val fibCurr = fibPrevPrev + fibPrev
                fibPrevPrev = fibPrev
                fibPrev = fibCurr
            }
        }
    }

}

