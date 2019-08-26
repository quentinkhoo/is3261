package com.quentin.is3261.tutorial4

import android.provider.BaseColumns

class TableInfo : BaseColumns {

    companion object {
        val TABLE_NAME = "course"
        val COLUMN_COURSECODE = "courseCode"
        val COLUMN_NUMBEROFSTUDENTS = "numberOfStudents"
        val COLUMN_LEVEL = "level"
    }


}