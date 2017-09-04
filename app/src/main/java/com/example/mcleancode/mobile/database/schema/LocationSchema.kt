package com.example.mcleancode.mobile.database.schema

import android.provider.BaseColumns

object LocationSchema {
    class Table: BaseColumns {
        companion object {
            val _ID = "_id"
            val TABLE_NAME = "location"
            val COLUMN_NAME_TITLE = "title"
            val COLUMN_NAME_LAT = "lat"
            val COLUMN_NAME_LONG = "long"
            val COLUMN_NAME_RADIUS = "radius"
            val COLUMN_NAME_ENTRY = "entry"
            val COLUMN_NAME_STATUS = "status"
        }
    }
}
