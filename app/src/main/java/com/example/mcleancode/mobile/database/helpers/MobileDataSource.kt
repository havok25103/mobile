package com.example.mcleancode.mobile.database.helpers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.mcleancode.mobile.database.schema.LocationSchema

class MobileDataSource(val context: Context) {
    private var mDatabase: SQLiteDatabase? = null
    private var mMobileDbHelper = MobileDbHelper(context)

    fun open() {
        mDatabase = mMobileDbHelper.writableDatabase
    }

    fun close() {
        mDatabase!!.close()
    }

    fun selectAllLocations(): Cursor {
        val query = mDatabase!!.query(
                LocationSchema.Table.TABLE_NAME,
                arrayOf(LocationSchema.Table.COLUMN_NAME_TITLE),
                null, // where clause
                null, // where params
                null, // group by
                null, // having
                null // order by
        )

        return query
    }
}