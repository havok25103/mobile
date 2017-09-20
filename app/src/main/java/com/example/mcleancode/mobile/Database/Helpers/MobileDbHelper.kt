package com.example.mcleancode.mobile.Database.Helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import com.example.mcleancode.mobile.Database.Schema.LocationSchema
import com.example.mcleancode.mobile.Database.Scripts.LocationScripts
import com.example.mcleancode.mobile.Database.Seeds.Seed

class MobileDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Mobile.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(LocationScripts.createLocationTable())
        db.beginTransaction()
        try {
            Seed.locations.forEach { (title, lat, long, radius, entry, status) ->
                val values = ContentValues()
                values.put(LocationSchema.Table.COLUMN_NAME_TITLE, title)
                values.put(LocationSchema.Table.COLUMN_NAME_LAT, lat)
                values.put(LocationSchema.Table.COLUMN_NAME_LONG, long)
                values.put(LocationSchema.Table.COLUMN_NAME_RADIUS, radius)
                values.put(LocationSchema.Table.COLUMN_NAME_ENTRY, entry)
                values.put(LocationSchema.Table.COLUMN_NAME_STATUS, status)
                db.insert(LocationSchema.Table.TABLE_NAME, null, values)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(LocationScripts.destroyLocationTable())
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}