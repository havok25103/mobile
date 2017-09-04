package com.example.mcleancode.mobile.game

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest

class GeofenceManager(val context: Context, val mGeofencingClient: GeofencingClient) {

    private var mGeofencePendingIntent: PendingIntent? = null

    fun addGeofencesToClient(geofenceList: MutableList<Geofence>) {
        val builder = GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofences(geofenceList)
                .build()

        mGeofencingClient.addGeofences(builder, pendingIntent())
            .addOnSuccessListener { Log.i("Geofence I On Success", "Trigger") }
            .addOnFailureListener { Log.i("Geofence I On Failure", "Trigger") }
    }

    private fun pendingIntent(): PendingIntent {
        if(mGeofencePendingIntent != null) {
            return  mGeofencePendingIntent!!
        }

        val intent = Intent(context, GeofenceTransitionsIntentService::class.java)
        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}