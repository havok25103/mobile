package com.example.mcleancode.mobile.UI.JournalEntries

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.Database.Helpers.MobileDataSource

class JournalEntriesActivity : FragmentActivity() {
    private var mMobileDataSource: MobileDataSource? = null
    private var mJournalEntriesPresenter: JournalEntriesPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_journal_entries)

        mMobileDataSource = MobileDataSource(this)

        mJournalEntriesPresenter = JournalEntriesPresenter(
                dataSource = mMobileDataSource!!,
                journalEntriesView = findViewById(R.id.journalEntriesView) as JournalEntriesView
        )
    }

    override fun onResume() {
        super.onResume()
        mMobileDataSource!!.open()
        mJournalEntriesPresenter!!.start()
    }

    override fun onPause() {
        super.onPause()
        mMobileDataSource!!.close()
    }
}
