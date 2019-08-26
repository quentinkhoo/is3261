package com.quentin.is3261.tutorial4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var courseDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.add_butt)
        btnAdd.setOnClickListener{
            addCourse()
        }

        val btnDelete = findViewById<Button>(R.id.del_butt)
        btnDelete.setOnClickListener{
            deleteCourse()
        }

        val btnDisplay = findViewById<Button>(R.id.disp_butt)
        btnDisplay.setOnClickListener{
            displayCourse()
        }

        courseDBHelper = DBHelper(this)
    }

    private fun addCourse() {
        var courseCode = this.et_courseCode.text.toString()
        var numberOfStudents = this.et_numberOfStudents.text.toString().toInt()
        var level  = this.et_level.text.toString()

        var result = courseDBHelper.insertCourse(DataRecord(courseCode, numberOfStudents, level))

        this.et_courseCode.setText("")
        this.et_numberOfStudents.setText("")
        this.et_level.setText("")

        this.result_display.text = "Added course - " + result
    }

    private fun deleteCourse() {
        var courseCode = this.et_courseCode.text.toString()
        val result = courseDBHelper.deleteCourse(courseCode)
        this.result_display.text = "Deleted Course - " + result
    }

    private fun displayCourse() {
        var courses = courseDBHelper.readAllCourses()

        var str: String? = null
        str = "Fetched " + courses.size + " courses: \n"

        courses.forEach {
            str = str + it.courseCode.toString() + " - " + it.numberOfStudents.toString() + " " + it.level + "\n"
        }

        this.result_display.text = "All Courses: " + str
    }
}
