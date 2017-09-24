package com.example.mcleancode.mobile.UI.Activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.ListView
import com.example.mcleancode.mobile.Abilities.JournalEntryAbility

import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.UI.JournalEntriesList.JournalEntriesListAdapter
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Finders.LocationFinder
import com.example.mcleancode.mobile.UI.JournalEntriesList.JournalEntriesListViewModel

class JournalEntriesActivity : FragmentActivity() {

    private val mLocations = ArrayList<JournalEntriesListViewModel>()
    private val mMobileDataSource = MobileDataSource(this)
    private val mLocationFinder = LocationFinder(mMobileDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal_entries)
    }

    override fun onResume() {
        super.onResume()
        mMobileDataSource.open()

        val listView = listView()

        fetchLocations()
        populateLocationsList(listView)
    }

    override fun onPause() {
        super.onPause()
        mMobileDataSource.close()
    }

    private fun listView(): ListView {
        return findViewById(R.id.journalEntriesList) as ListView
    }

    private fun populateLocationsList(listView: ListView) {
        val arrayAdapter = JournalEntriesListAdapter(this, filteredLocations())
        listView.adapter = arrayAdapter
    }

    private fun filteredLocations(): ArrayList<JournalEntriesListViewModel> {
        val locations = mLocations.filter { item ->
            JournalEntryAbility.canView(item.status)
        }

        return ArrayList(locations)
    }

    private fun fetchLocations() {
        val foundLocations = mLocationFinder.findAll()

        mLocations.clear()

        foundLocations.forEach { location ->
            val viewModel = JournalEntriesListViewModel(location.id, location.status, location.title)
            mLocations.add(viewModel)
        }
    }
}
