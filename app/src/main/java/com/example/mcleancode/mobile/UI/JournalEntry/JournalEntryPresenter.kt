package com.example.mcleancode.mobile.UI.JournalEntry

import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Finders.LocationFinder

class JournalEntryPresenter(
        val locationId: String,
        val dataSource: MobileDataSource,
        val journalEntryView: JournalEntryContract.View): JournalEntryContract.Presenter {

    private val mLocationFinder = LocationFinder(dataSource)

    init {
        journalEntryView.setPresenter(this)
    }

    override fun start() {
        openJournalEntry(locationId)
    }

    override fun openJournalEntry(locationId: String) {
        val location = mLocationFinder.findById(locationId)

        journalEntryView.showTitle(location.title)
        journalEntryView.showText(location.entry)
    }
}
