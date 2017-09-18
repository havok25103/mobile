package com.example.mcleancode.mobile.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import com.example.mcleancode.mobile.viewmodels.JournalEntriesListViewModel
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.activity.JournalEntryActivity

class JournalEntriesListAdapter(context: Context, items: ArrayList<JournalEntriesListViewModel>):
        ArrayAdapter<JournalEntriesListViewModel>(context, 0, items) {

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
        setFont(nameTextViewInstance)

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

    }

    private fun setFont(textView: TextView) {
        val font = Typeface.createFromAsset(context.assets, "fonts/SourceSansProRegular.ttf")
        textView.setTypeface(font, Typeface.NORMAL)
    }

    private fun registerEvents(convertView: View, journalEntry: JournalEntriesListViewModel) {
        convertView.setOnClickListener {
            val intent = Intent(context, JournalEntryActivity::class.java)
            intent.putExtra("LOCATION_ID", journalEntry.id.toString())
            context.startActivity(intent)
        }
    }
}