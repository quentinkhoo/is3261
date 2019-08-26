package com.helloworld.sylvia.tutorial4

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

        val btnAdd = findViewById<Button>(R.id.btn_Add)
        btnAdd.setOnClickListener{
            addCourse()
        }

        val btnDelete = findViewById<Button>(R.id.btn_Delete)
        btnDelete.setOnClickListener{
            deleteCourse()
        }

        val btnDisplay = findViewById<Button>(R.id.btn_Display)
        btnDisplay.setOnClickListener{
            displayCourse()
        }

        courseDBHelper = DBHelper(this)
    }

    fun addCourse() {
        var courseCode = this.et_Course.text.toString()
        var level = this.et_indicateGrad.text.toString()
        var numberOfStudents  = this.et_personNo.text.toString().toInt()

        var result = courseDBHelper.insertCourse(DataRecord(courseCode,numberOfStudents,level))

        this.et_Course.setText("")
        this.et_indicateGrad.setText("")
        this.et_personNo.setText("")

        this.tv_Display.text = "Added course: " + result

        Toast.makeText(this, "Added course : " + result, Toast.LENGTH_LONG).show()

    }

    fun deleteCourse() {
        var courseCode = this.et_Course.text.toString()
        val result = courseDBHelper.deleteCourse(courseCode)
        this.tv_Display.text = "Deleted Course: " + result
        Toast.makeText(this, "Deleted Course : " + result, Toast.LENGTH_LONG).show()
    }

    fun displayCourse() {
        var course = courseDBHelper.readAllCourse()

        var str: String? = null
        str = "Fetched " + course.size + " courses: \n"

        course.forEach {
            str = str + it.courseCode.toString() + " - " + it.numberOfStudents.toString() + " " + it.level + "\n"
        }

        this.tv_Display.text = "All People: " + str
    }
}
