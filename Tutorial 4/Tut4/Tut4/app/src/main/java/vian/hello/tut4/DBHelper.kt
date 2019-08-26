package vian.hello.tut4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

    val DATABASE_VERSION = 1
    val DATABASE_NAME = "People.db"

    // must have spacing after CREATE TABLE (SPACE)
    private val SQL_CREATE_ENTRIES = "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
            TableInfo.COLUMN_COURSE + " TEXT PRIMARY KEY, " +
            TableInfo.COLUMN_NUMOFSTUDENTS + " TEXT, " +
            TableInfo.COLUMN_LEVEL + " TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    // helper functions to insert records
    fun insertRecord(record: DataRecord) : Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(TableInfo.COLUMN_COURSE, record.courseCode)
        values.put(TableInfo.COLUMN_NUMOFSTUDENTS, record.numberOfStudents)
        values.put(TableInfo.COLUMN_LEVEL, record.studentLevel)

        val newRowId = db.insert(TableInfo.TABLE_NAME, null, values)
        return true
    }

    fun deleteRecord(courseCode: String) : Boolean {
        val db = writableDatabase
        val selection = TableInfo.COLUMN_COURSE + " LIKE?"
        val selectionArgs = arrayOf(elements = courseCode)

        db.delete(TableInfo.TABLE_NAME, selection, selectionArgs)
        return true
    }

    fun readRecord(courseCode: String) : ArrayList<DataRecord> {
        val people = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("select * from " +
                    TableInfo.TABLE_NAME + " WHERE " +
                    TableInfo.COLUMN_COURSE + "=" + courseCode + "", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var numberOfStudents : Int
        var level : String

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) { // not at the end of the result list
                numberOfStudents = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_NUMOFSTUDENTS))
                level = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_LEVEL))
                people.add(DataRecord(courseCode, numberOfStudents, level))
                cursor.moveToNext()
            }
        }
        return people
    }

    fun readAllRecords(): ArrayList<DataRecord> {
        val records = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + TableInfo.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var courseCode : String
        var numberOfStudents : Int
        var level : String

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                courseCode = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_COURSE))
                numberOfStudents = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_NUMOFSTUDENTS))
                level = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_LEVEL))

                records.add(DataRecord(courseCode, numberOfStudents, level))
                cursor.moveToNext()
            }
        }
        return records
    }





}