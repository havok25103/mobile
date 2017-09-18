package com.example.mcleancode.mobile.UI.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource
import com.example.mcleancode.mobile.Database.Schema.LocationSchema

class JournalEntryActivity : AppCompatActivity() {

    private var mMobileDataSource: MobileDataSource? = null
    private var journalTitle = ""
    private var journalEntry = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal_entry)
        mMobileDataSource = MobileDataSource(this)
    }

    override fun onResume() {
        super.onResume()
        mMobileDataSource!!.open()
        fetchJournalEntry()
        updateView()
    }

    override fun onPause() {
        super.onPause()
        mMobileDataSource!!.close()
    }

    private fun fetchJournalEntry() {
        val id = intent.getStringExtra("LOCATION_ID")
        val cursor = mMobileDataSource!!.selectSingleLocation(id)

        cursor.moveToFirst()

        val nameIndex = cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_TITLE)
        val entryIndex = cursor.getColumnIndex(LocationSchema.Table.COLUMN_NAME_ENTRY)

        journalTitle = cursor.getString(nameIndex)
        journalEntry = cursor.getString(entryIndex)

        cursor.close()
    }

    private fun updateView() {
        titleView().text = journalTitle
        entryView().text = journalEntry
    }

    private fun titleView(): TextView {
        return findViewById(R.id.journalEntryTitle) as TextView
    }

    private fun entryView(): TextView {
        return findViewById(R.id.journalEntryText) as TextView
    }
}
