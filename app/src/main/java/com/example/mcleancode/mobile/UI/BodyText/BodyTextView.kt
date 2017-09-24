package com.example.mcleancode.mobile.UI.BodyText

import android.content.Context
import android.widget.TextView
import android.util.AttributeSet
import com.example.mcleancode.mobile.UI.Interfaces.IFontSettable


class BodyTextView: TextView, IFontSettable {
    private val fontFamily = "fonts/SourceSansProRegular.ttf"

    constructor(context: Context): super(context) {
        setFont(context, this, fontFamily)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setFont(context, this, fontFamily)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setFont(context, this, fontFamily)
    }
}