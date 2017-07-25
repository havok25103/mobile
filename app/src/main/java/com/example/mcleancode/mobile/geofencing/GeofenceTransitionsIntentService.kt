package com.example.mcleancode.mobile.geofencing

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import android.widget.Toast
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.activity.JournalEntryActivity

class GeofenceTransitionsIntentService: IntentService("GeofenceTransitionsIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        handleAnyErrors(this, geofencingEvent)
        transitionToJournalEntryActivity()
        toastOnEnterAndExit(geofencingEvent)
    }

    private fun handleAnyErrors(context: Context, geofencingEvent: GeofencingEvent) {
        if(geofencingEvent.hasError()) {
            val errorMessage = getErrorString(context, geofencingEvent.errorCode)
            Log.e("GeofenceTransitions", errorMessage)
        }
    }

    private fun getErrorString(context: Context, errorCode: Int):String {
        val resources = context.resources

        when(errorCode) {
            GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> return resources.getString(R.string.geofence_not_available)
            GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> return resources.getString(R.string.geofence_too_many_geofences)
            GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> return resources.getString(R.string.geofence_too_many_pending_intents)
            else -> return resources.getString(R.string.geofence_unknown_error)
        }
    }

    private fun transitionToJournalEntryActivity() {
        val intent = Intent(this, JournalEntryActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun toastOnEnterAndExit(geofencingEvent: GeofencingEvent) {
        when(geofencingEvent.geofenceTransition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> toast("Enter")
            Geofence.GEOFENCE_TRANSITION_EXIT -> toast("Exit")
            else ->  toast("Unknown")
        }
    }

    private fun toast(text: String) {
        val mainHandler = Handler(mainLooper)

        mainHandler.post {
            val context = applicationContext
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }
}