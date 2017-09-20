package com.example.mcleancode.mobile.UI.JournalEntriesList

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.mcleancode.mobile.Abilities.JournalEntryAbility
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.UI.Activities.JournalEntryActivity
import com.example.mcleancode.mobile.UI.Interfaces.FontSettable

class JournalEntriesListAdapter(context: Context, items: ArrayList<JournalEntriesListViewModel>):
        ArrayAdapter<JournalEntriesListViewModel>(context, 0, items),
        FontSettable {

    private val fontFamily = "fonts/SourceSansProRegular.ttf"

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val journalEntry = getItem(position)

        if(convertView == null) {
            convertView = inflateItemView(parent)
        }

        val statusImageViewInstance = statusView(convertView)
        val nameTextViewInstance = nameTextView(convertView)

        setItemStatus(statusImageViewInstance, journalEntry)
        setItemName(nameTextViewInstance, journalEntry)
        setFont(context, nameTextViewInstance, fontFamily)

        registerEvents(convertView, journalEntry)

        return convertView
    }

    private fun inflateItemView(parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.journal_entry_list_item_view, parent, false)
    }

    private fun nameTextView(convertView: View): TextView {
        return convertView.findViewById(R.id.nameTextView) as TextView
    }

    private fun statusView(convertView: View): ImageView {
        return convertView.findViewById(R.id.statusView) as ImageView
    }

    private fun setItemName(textView: TextView, journalEntry: JournalEntriesListViewModel) {
        textView.text = journalEntry.name
    }

    private fun setItemStatus(imageView: ImageView, journalEntry: JournalEntriesListViewModel) {
        val status = journalEntry.status

        if(JournalEntryAbility.needsReading(status)) {
            imageView.setImageResource(R.drawable.alert_icon)
        }
    }

    private fun registerEvents(convertView: View, journalEntry: JournalEntriesListViewModel) {
        convertView.setOnClickListener {
            val intent = Intent(context, JournalEntryActivity::class.java)
            intent.putExtra("LOCATION_ID", journalEntry.id.toString())
            context.startActivity(intent)
        }
    }
}