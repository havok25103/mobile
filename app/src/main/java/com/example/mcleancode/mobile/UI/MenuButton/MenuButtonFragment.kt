package com.example.mcleancode.mobile.UI.MenuButton

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.UI.Activities.MenuActivity

class MenuButtonFragment: Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstance: Bundle?): View {
        val v = inflater.inflate(R.layout.menu_button_fragment, container, false)
        registerMenuButton(v)
        return v
    }

    private fun registerMenuButton(view: View) {
        val button = view.findViewById(R.id.menuButton)
        button.setOnClickListener {
            val intent = Intent(activity, MenuActivity::class.java)
            activity.startActivity(intent)
        }
    }
}