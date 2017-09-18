package com.example.mcleancode.mobile.UI.MenuButton

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import com.example.mcleancode.mobile.UI.Interfaces.FontSettable

class MenuButtonView: Button, FontSettable {
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