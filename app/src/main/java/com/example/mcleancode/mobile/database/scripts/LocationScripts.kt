package com.example.mcleancode.mobile.database.scripts

import com.example.mcleancode.mobile.database.schema.LocationSchema

object LocationScripts {
    fun createLocationTable(): String {
        return "CREATE TABLE " + LocationSchema.Table.TABLE_NAME + " (" +
                LocationSchema.Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LocationSchema.Table.COLUMN_NAME_NAME + " TEXT," +
                LocationSchema.Table.COLUMN_NAME_LAT + " FLOAT," +
                LocationSchema.Table.COLUMN_NAME_LONG + " FLOAT," +
                LocationSchema.Table.COLUMN_NAME_RADIUS + " INTEGER," +
                LocationSchema.Table.COLUMN_NAME_ENTRY + " TEXT," +
                LocationSchema.Table.COLUMN_NAME_STATUS + " INTEGER)"
    }

    fun destroyLocationTable(): String {
        return "DROP TABLE IF EXISTS" + LocationSchema.Table.TABLE_NAME
    }
}