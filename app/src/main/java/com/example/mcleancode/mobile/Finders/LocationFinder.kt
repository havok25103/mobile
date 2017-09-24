package com.example.mcleancode.mobile.Finders

import android.content.ContentValues
import android.database.Cursor
import com.example.mcleancode.mobile.Finders.Common.IFinder
import com.example.mcleancode.mobile.Database.Entities.LocationEntity
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Database.Schema.LocationSchema

class LocationFinder(val dataSource: MobileDataSource): IFinder {

    override fun findAll(): ArrayList<LocationEntity> {
        val locations = ArrayList<LocationEntity>()
        val cursor = findAllQuery()

        cursor.moveToFirst()

        while(!cursor.isAfterLast) {
            val location = buildLocationEntity(cursor)
            locations.add(location)
            cursor.moveToNext()
        }

        cursor.close()

        return locations
    }

    override fun findById(id: String): LocationEntity {
        val cursor = findByIdQuery(id)
        cursor.moveToFirst()
        val location = buildLocationEntity(cursor)
        cursor.close()

        return location
    }

    override fun update(id: String, values: ContentValues) {
        updateQuery(id, values)
    }

    private fun findAllQuery(): Cursor {
        return dataSource.database.query(
            LocationSchema.Table.TABLE_NAME,
            locationColumnNames(),
            null, // where clause
            null, // where params
            null, // group by
            null, // having
            null // order by
        )
    }

    private fun findByIdQuery(id: String): Cursor {
        val whereClause = "_ID = ?"

        return dataSource.database.query (
            LocationSchema.Table.TABLE_NAME,
            locationColumnNames(),
            whereClause,
            arrayOf(id),
            null, // group by
            null, // having
            null // order by
        )
    }

    private fun updateQuery(id: String, values: ContentValues): Int {
        val whereClause = "_ID = ?"

        return dataSource.database.update(
            LocationSchema.Table.TABLE_NAME,
            values,
            whereClause,
            arrayOf(id)
        )
    }

    private fun locationColumnNames(): Array<String> {
        return arrayOf(
            LocationSchema.Table._ID,
            LocationSchema.Table.COLUMN_NAME_TITLE,
            LocationSchema.Table.COLUMN_NAME_ENTRY,
            LocationSchema.Table.COLUMN_NAME_LAT,
            LocationSchema.Table.COLUMN_NAME_LONG,
            LocationSchema.Table.COLUMN_NAME_RADIUS,
            LocationSchema.Table.COLUMN_NAME_STATUS
        )
    }

    private fun buildLocationEntity(cursor: Cursor): LocationEntity {
        return LocationEntity(
            id = cursor.getInt(cursor.getColumnIndex(LocationSchema.Table._ID)),
            title = cursor.getString(cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_TITLE)),
            entry = cursor.getString(cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_ENTRY)),
            lat = cursor.getFloat(cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_LAT)),
            long = cursor.getFloat(cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_LONG)),
            radius = cursor.getInt(cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_RADIUS)),
            status = cursor.getInt(cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_STATUS))
        )
    }
}
