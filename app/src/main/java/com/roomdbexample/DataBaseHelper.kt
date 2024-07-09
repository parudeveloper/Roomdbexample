package com.roomdbexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DataBaseConstants.DATABASE_NAME,
    null,
    DataBaseConstants.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
                  CREATE TABLE ${DataBaseConstants.TABLE_NAME} (
                         ${DataBaseConstants.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                         ${DataBaseConstants.COLUMN_NAME} TEXT,
                         ${DataBaseConstants.COLUMN_DESCRIPTION} TEXT)
        """.trimMargin()

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun readData(): List<NotesPojo> {
        val datalist = mutableListOf<NotesPojo>()

        val curser: Cursor = readableDatabase.query(
            DataBaseConstants.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
        )

        with(curser) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DataBaseConstants.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(DataBaseConstants.COLUMN_NAME))
                val description = getString(getColumnIndexOrThrow(DataBaseConstants.COLUMN_DESCRIPTION))
                datalist.add(NotesPojo(id, name,description))
            }

        }
        return datalist
    }

    fun insertData(title: String,description : String): Long {

        val values = ContentValues().apply {
            put(DataBaseConstants.COLUMN_NAME, title)
            put(DataBaseConstants.COLUMN_DESCRIPTION, description)
        }

        return writableDatabase.insert(DataBaseConstants.TABLE_NAME, null, values)
    }

    fun update(id: Long,name: String,description : String) : Int{
        val values = ContentValues().apply {
            put(DataBaseConstants.COLUMN_NAME, name)
            put(DataBaseConstants.COLUMN_DESCRIPTION, description)

        }
        val selection = "${DataBaseConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        return writableDatabase.update(DataBaseConstants.TABLE_NAME,values, selection, selectionArgs)
    }

    fun delete(id: Long) : Int{
        val selection = "${DataBaseConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        return writableDatabase.delete(DataBaseConstants.TABLE_NAME,selection,selectionArgs)
    }
}