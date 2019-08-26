package vian.hello.tut4

import android.provider.BaseColumns

class TableInfo : BaseColumns {

    companion object {
        val TABLE_NAME = "Students"
        val COLUMN_COURSE = "CourseCode"
        val COLUMN_NUMOFSTUDENTS = "NumberOfStudents"
        val COLUMN_LEVEL = "Level"
    }

}