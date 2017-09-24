package com.example.mcleancode.mobile.UI.HeaderText

import android.content.Context
import android.widget.TextView
import android.util.AttributeSet
import com.example.mcleancode.mobile.UI.Common.IFontSettable


class HeaderTextView: TextView, IFontSettable {
    private val fontFamily = "fonts/OrbitronRegular.ttf"

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