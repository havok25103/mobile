package com.example.mcleancode.mobile.UI.JournalEntry

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mcleancode.mobile.R

class JournalEntryView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs), JournalEntryContract.View {

    private var mPresenter: JournalEntryContract.Presenter? = null

    init {
        inflate(context, R.layout.view_journal_entry, this)
    }

    override fun setPresenter(presenter: JournalEntryContract.Presenter) {
        mPresenter = presenter
    }

    override fun showTitle(title: String) {
        val view = findViewById(R.id.journalEntryTitle) as TextView
        view.text = title
    }

    override fun showText(text: String) {
        val view = findViewById(R.id.journalEntryText) as TextView
        view.text = text
    }
}