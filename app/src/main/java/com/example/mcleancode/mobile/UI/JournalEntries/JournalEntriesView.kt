package com.example.mcleancode.mobile.UI.JournalEntries

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ListView
import com.example.mcleancode.mobile.R

class JournalEntriesView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs), JournalEntriesContract.View {
    private var mPresenter: JournalEntriesContract.Presenter? = null

    init {
        inflate(context, R.layout.view_journal_entries, this)
    }

    override fun setPresenter(presenter: JournalEntriesContract.Presenter) {
        mPresenter = presenter
    }

    override fun populateLocationsList(locations: ArrayList<JournalEntriesListViewModel>) {
        val view = findViewById(R.id.journalEntriesList) as ListView
        val arrayAdapter = JournalEntriesListAdapter(context, locations)
        view.adapter = arrayAdapter
    }
}