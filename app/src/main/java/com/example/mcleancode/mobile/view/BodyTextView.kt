package com.example.mcleancode.mobile.view

import android.content.Context
import android.widget.TextView
import android.graphics.Typeface
import android.util.AttributeSet


class BodyTextView: TextView {
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
        val font = Typeface.createFromAsset(context.assets, "fonts/SourceSansProRegular.ttf")
        setTypeface(font, Typeface.NORMAL)
    }
}