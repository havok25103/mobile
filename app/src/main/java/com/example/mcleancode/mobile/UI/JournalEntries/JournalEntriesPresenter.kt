package com.example.mcleancode.mobile.UI.JournalEntries

import com.example.mcleancode.mobile.Abilities.JournalEntryAbility
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Finders.LocationFinder

class JournalEntriesPresenter(dataSource: MobileDataSource,
                              val journalEntriesView: JournalEntriesContract.View): JournalEntriesContract.Presenter {

    private val mLocationFinder = LocationFinder(dataSource)

    init {
        journalEntriesView.setPresenter(this)
    }

    override fun start() {
        openJournalEntries()
    }

    override fun openJournalEntries() {
        val locations = fetchLocations()
        val filteredLocations = filteredLocations(locations)

        journalEntriesView.populateLocationsList(filteredLocations)
    }

    private fun filteredLocations(locations: ArrayList<JournalEntriesListViewModel>): ArrayList<JournalEntriesListViewModel> {
        val filteredLocations = locations.filter { item ->
            JournalEntryAbility.canView(item.status)
        }

        return ArrayList(filteredLocations)
    }

    private fun fetchLocations(): ArrayList<JournalEntriesListViewModel> {
        val locations = ArrayList<JournalEntriesListViewModel>()
        val foundLocations = mLocationFinder.findAll()

        foundLocations.forEach { location ->
            val viewModel = JournalEntriesListViewModel(location.id, location.status, location.title)
            locations.add(viewModel)
        }

        return locations
    }
}
