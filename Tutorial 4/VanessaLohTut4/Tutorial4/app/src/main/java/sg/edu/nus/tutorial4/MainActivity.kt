package sg.edu.nus.tutorial4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.text.method.ScrollingMovementMethod
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var courseDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butAdd = findViewById<Button>(R.id.but_add)
        butAdd.setOnClickListener {
            addCourse()
        }

        val butDelete = findViewById<Button>(R.id.but_del)
        butDelete.setOnClickListener {
            deleteCourse()
        }

        val butDisplay = findViewById<Button>(R.id.but_display)
        butDisplay.setOnClickListener {
            displayCourse()
        }

        val textView = findViewById<TextView>(R.id.text_display)
        textView.movementMethod = ScrollingMovementMethod()

        courseDBHelper = DBHelper(this)
    }

    private fun addCourse() {
        var coursecode = this.edit_courseCode.text.toString()
        var numstudents = this.edit_numStudents.text.toString().toInt()
        var level = this.edit_level.text.toString()

        var result = courseDBHelper.insertCourse(DataRecord(coursecode, numstudents, level))

        this.edit_courseCode.setText("")
        this.edit_numStudents.setText("")
        this.edit_level.setText("")

        this.text_display.text = "Added course: " + result
    }

    private fun deleteCourse() {
        var coursecode = this.edit_courseCode.text.toString()
        var result = courseDBHelper.deleteCourse(coursecode)
        this.text_display.text = "Deleted course: " + result
    }

    private fun displayCourse() {
        var course = courseDBHelper.readAllCourses()

        var str: String? = null
        str = "Fetched " + course.size + " courses: \n"

        course.forEach {
            str = str + it.courseCode + " - " + it.numberOfStudents + " " + it.level + "\n"
        }
        this.text_display.text = "All courses: " + str
    }
}