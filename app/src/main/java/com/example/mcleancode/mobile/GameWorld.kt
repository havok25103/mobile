package com.example.mcleancode.mobile

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

class GameWorld(val context: Context) {

    private val beacons: Array<Beacon> = arrayOf(
            Beacon(
                    id = "Vespr",
                    centerLong = -0.1276250,
                    centerLat = 51.5033640,
                    radiusInMeters = 20.0f
            )
    )

    private val geofenceList: MutableList<Geofence> = arrayListOf()
    private val mGeofencingClient: GeofencingClient? = LocationServices.getGeofencingClient(context)
    private var mGeofencePendingIntent: PendingIntent? = null

    fun addBeacons() {
        beacons.forEach { beacon -> geofenceList.add(beacon.geofence) }
        buildGeofences()
    }

    private fun buildGeofences() {
        val builder = GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofences(geofenceList)
                .build()

        mGeofencingClient!!.addGeofences(builder, pendingIntent())
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