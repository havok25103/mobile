package com.example.mcleancode.mobile.UI.Interfaces

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

interface FontSettable {
    fun setFont(context: Context, textView: TextView, fontFamily: String) {
        val font = Typeface.createFromAsset(context.assets, fontFamily)
        textView.setTypeface(font, Typeface.NORMAL)
    }
}