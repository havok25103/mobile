package com.example.mcleancode.mobile.UI.HeaderText

import android.content.Context
import android.widget.TextView
import android.graphics.Typeface
import android.util.AttributeSet


class HeaderTextView: TextView {
    constructor(context: Context): super(context) {
        setFont()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setFont()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setFont()
    }

    private fun setFont() {
        val font = Typeface.createFromAsset(context.assets, "fonts/OrbitronRegular.ttf")
        setTypeface(font, Typeface.NORMAL)
    }
}