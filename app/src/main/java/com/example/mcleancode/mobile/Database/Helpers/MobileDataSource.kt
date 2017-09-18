package com.example.mcleancode.mobile.Database.Helpers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.mcleancode.mobile.Database.Schema.LocationSchema

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
                arrayOf(
                        LocationSchema.Table._ID,
                        LocationSchema.Table.COLUMN_NAME_STATUS,
                        LocationSchema.Table.COLUMN_NAME_TITLE
                ),
                null, // where clause
                null, // where params
                null, // group by
                null, // having
                null // order by
        )

        return query
    }

    fun selectSingleLocation(id: String): Cursor {
        val whereClause = "_ID = ?"

        val query = mDatabase!!.query (
                LocationSchema.Table.TABLE_NAME,
                arrayOf(
                        LocationSchema.Table._ID,
                        LocationSchema.Table.COLUMN_NAME_TITLE,
                        LocationSchema.Table.COLUMN_NAME_ENTRY
                ),
                whereClause,
                arrayOf(id),
                null, // group by
                null, // having
                null // order by
        )

        return query
    }
}