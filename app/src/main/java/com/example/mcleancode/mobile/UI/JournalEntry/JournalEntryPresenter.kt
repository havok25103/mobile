package com.example.mcleancode.mobile.UI.JournalEntry

import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Database.Schema.LocationSchema

class JournalEntryPresenter(
        val locationId: String,
        val store: MobileDataSource,
        val journalEntryView: JournalEntryContract.View): JournalEntryContract.Presenter {

    init {
        journalEntryView.setPresenter(this)
    }

    override fun start() {
        openJournalEntry(locationId)
    }

    override fun openJournalEntry(locationId: String) {
        val cursor = store.selectSingleLocation(locationId)

        cursor.moveToFirst()

        val nameIndex = cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_TITLE)
        val entryIndex = cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_ENTRY)

        val journalEntryTitle = cursor.getString(nameIndex)
        val journalEntryText = cursor.getString(entryIndex)

        journalEntryView.showTitle(journalEntryTitle)
        journalEntryView.showText(journalEntryText)

        cursor.close()
    }
}
