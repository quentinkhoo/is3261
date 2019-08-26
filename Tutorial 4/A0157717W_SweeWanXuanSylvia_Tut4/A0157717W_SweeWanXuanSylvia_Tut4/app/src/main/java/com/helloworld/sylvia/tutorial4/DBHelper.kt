package com.helloworld.sylvia.tutorial4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context,
        DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "course.db"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
                        TableInfo.COLUMN_COURSECODE + " TEXT PRIMARY KEY," +
                        TableInfo.COLUMN_STUDENTSNO + " TEXT," +
                        TableInfo.COLUMN_LEVEL + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
                TableInfo.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?,
                             oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertCourse(course: DataRecord): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(TableInfo.COLUMN_COURSECODE, course.courseCode)
        values.put(TableInfo.COLUMN_STUDENTSNO, course.numberOfStudents)
        values.put(TableInfo.COLUMN_LEVEL, course.level)

        val newRowId = db.insert(TableInfo.TABLE_NAME,
                null, values)
        return true
    }

    fun deleteCourse(courseCode: String):Boolean {
        val db = writableDatabase
        val selection = TableInfo.COLUMN_COURSECODE + " LIKE ?"
        val selectionArgs = arrayOf(courseCode)

        db.delete(TableInfo.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readCourse(courseCode: String): ArrayList<DataRecord> {
        val course = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("select * from " +
                    TableInfo.TABLE_NAME + " WHERE " +
                    TableInfo.COLUMN_COURSECODE + "=" +  courseCode +  "", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var numberOfStudents: Int
        var level: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                numberOfStudents = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_STUDENTSNO))
                level= cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_LEVEL))
                course.add(DataRecord(courseCode, numberOfStudents, level))
                cursor.moveToNext()
            }
        }
        return course
    }

    fun readAllCourse(): ArrayList<DataRecord> {
        val course = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from "+ TableInfo.TABLE_NAME, null)

        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var courseCode: String
        var level: String
        var numberOfStudents: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                courseCode = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_COURSECODE))
                level = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_LEVEL))
                numberOfStudents = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_STUDENTSNO))
                course.add(DataRecord(courseCode, numberOfStudents, level))
                cursor.moveToNext()
            }
        }
        return course
    }

}