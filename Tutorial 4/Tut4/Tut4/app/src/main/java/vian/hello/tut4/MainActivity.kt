package vian.hello.tut4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recordDBHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener{
            addRecord()
        }

        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        btnDisplay.setOnClickListener{
            displayRecord()
        }

        val btnDelete = findViewById<Button>(R.id.btnDelete)
        btnDelete.setOnClickListener{
            deleteRecord()
        }

        recordDBHelper = DBHelper(this)
    }

    fun addRecord() {
        var courseCode = this.etCourse.text.toString()
        var numberOfStudents = this.etNumber.text.toString().toInt()
        var level = this.etLevel.text.toString()

        var result = recordDBHelper.insertRecord(DataRecord(courseCode, numberOfStudents, level))

        this.etCourse.setText("")
        this.etNumber.setText("")
        this.etLevel.setText("")

        this.tvDisplay.text = "Added record: " + result

        Toast.makeText(this, "Added record : " + result, Toast.LENGTH_LONG).show()
    }

    fun deleteRecord() {

        var courseCode = this.etCourse.text.toString()
        val result = recordDBHelper.deleteRecord(courseCode)
        this.tvDisplay.text = "Deleted user " + result

        Toast.makeText(this, "Deleted Person : " + result, Toast.LENGTH_LONG).show()
    }

    fun displayRecord() {

        var records = recordDBHelper.readAllRecords()

        var str:String? = null
        str = "Fetched " + records.size + " courses: \n"

        records.forEach{
            str = str + it.courseCode.toString() + " - " + it.numberOfStudents + " - " + it.studentLevel + "\n"
        }

        this.tvDisplay.text = "All Courses: " + str
    }
}
