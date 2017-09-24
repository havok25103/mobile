package com.example.mcleancode.mobile.Database.Helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MobileDataSource(val context: Context) {

    private var mMobileDbHelper = MobileDbHelper(context)
    private var mDatabase: SQLiteDatabase? = null

    val database
        get() = mDatabase!!

    fun open() {
        mDatabase = mMobileDbHelper.writableDatabase
    }

    fun close() {
        mDatabase!!.close()
    }
}