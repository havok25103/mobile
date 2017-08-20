package com.example.mcleancode.mobile.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.mcleancode.mobile.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        registerNavigation()
    }

    private fun navigationOptions() = arrayOf(
        arrayOf( R.id.scannerButton, MainActivity::class.java),
        arrayOf( R.id.journalEntriesButton, JournalEntriesActivity::class.java),
        arrayOf( R.id.caseFileButton, CaseFileActivity::class.java)
    )

    private fun registerNavigation() {
        navigationOptions().forEach { navigationOption ->
            val button = findViewById(navigationOption[0] as Int)
            button.setOnClickListener {
                val intent = Intent(this, navigationOption[1] as Class<*>)
                startActivity(intent)
            }
        }
    }
}
