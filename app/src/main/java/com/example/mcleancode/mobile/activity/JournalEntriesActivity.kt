package com.example.mcleancode.mobile.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.ArrayAdapter
import android.widget.ListView

import com.example.mcleancode.mobile.R

class JournalEntriesActivity : FragmentActivity() {

    private val entries = arrayOf("Foxtail", "Lake Eola", "Hermans Loan Office")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal_entries)
        val listView = listView()
        populateEntriesList(listView)
        registerEntriesEvents(listView)
    }

    private fun listView(): ListView {
        return findViewById(R.id.journalEntriesList) as ListView
    }

    private fun populateEntriesList(listView: ListView) {
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, entries)
        listView.adapter = arrayAdapter
    }

    private fun registerEntriesEvents(listView: ListView) {
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, JournalEntryActivity::class.java)
            startActivity(intent)
        }
    }
}
