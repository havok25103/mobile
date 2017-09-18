package com.example.mcleancode.mobile.UI.Activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.ListView

import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.UI.JournalEntriesList.JournalEntriesListAdapter
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Database.Schema.LocationSchema
import com.example.mcleancode.mobile.UI.JournalEntriesList.JournalEntriesListViewModel

class JournalEntriesActivity : FragmentActivity() {

    private val mLocations = ArrayList<JournalEntriesListViewModel>()
    private var mMobileDataSource: MobileDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal_entries)
        mMobileDataSource = MobileDataSource(this)
    }

    override fun onResume() {
        super.onResume()

        mMobileDataSource!!.open()

        val listView = listView()

        fetchLocations()
        populateLocationsList(listView)
    }

    override fun onPause() {
        super.onPause()
        mMobileDataSource!!.close()
    }

    private fun listView(): ListView {
        return findViewById(R.id.journalEntriesList) as ListView
    }

    private fun populateLocationsList(listView: ListView) {
        val arrayAdapter = JournalEntriesListAdapter(this, mLocations)
        listView.adapter = arrayAdapter
    }

    private fun fetchLocations() {
        mLocations.clear()

        val cursor = mMobileDataSource!!.selectAllLocations()

        cursor.moveToFirst()

        while(!cursor.isAfterLast) {
            val idIndex = cursor.getColumnIndex(LocationSchema.Table._ID)
            val nameIndex = cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_TITLE)
            val statusIndex = cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_STATUS)

            val id = cursor.getInt(idIndex)
            val status = cursor.getInt(statusIndex)
            val name = cursor.getString(nameIndex)

            val viewModel = JournalEntriesListViewModel(id, status, name)

            mLocations.add(viewModel)

            cursor.moveToNext()
        }
        cursor.close()
    }
}
