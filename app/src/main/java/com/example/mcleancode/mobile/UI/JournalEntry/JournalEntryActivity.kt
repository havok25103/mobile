package com.example.mcleancode.mobile.UI.JournalEntry

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource

class JournalEntryActivity : AppCompatActivity() {
    private var mMobileDataSource: MobileDataSource? = null
    private var mJournalEntryPresenter: JournalEntryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_journal_entry)

        mMobileDataSource = MobileDataSource(this)

        mJournalEntryPresenter = JournalEntryPresenter(
            locationId = intent.getStringExtra("LOCATION_ID"),
            store = mMobileDataSource!!,
            journalEntryView = findViewById(R.id.journalEntryView) as JournalEntryView
        )
    }

    override fun onResume() {
        super.onResume()
        mMobileDataSource!!.open()
        mJournalEntryPresenter!!.start()
    }

    override fun onPause() {
        super.onPause()
        mMobileDataSource!!.close()
    }
}
